package org.klose.scheme;


import org.apache.commons.lang3.StringUtils;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SFrame;
import org.klose.scheme.service.EvalService;
import org.klose.scheme.type.SObject;
import org.klose.scheme.utils.InitEnv;
import org.klose.scheme.utils.SParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * main repl process
 */
public class Application {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        SFrame rootEnv = InitEnv.init();

        //repl: loop of read -> eval -> print -> read
        final Runnable repl = () -> {
            String expression = "";
            String line;
            boolean newEval = true;
            while (true) {
                if (newEval)
                    System.out.print(">> ");

                try {
                    line = console.readLine();
                    if (line == null)
                        System.exit(0);

                    expression = expression + " " + line + " ";
                    if (inputNotFinished(expression)) {
                        newEval = false;
                        continue;
                    }

                    if (!StringUtils.isEmpty(expression.trim())) {
                        SExpression exp = SParser.parse(expression);
                        SObject val = EvalService.eval(exp, rootEnv);
                        System.out.println(val);
                    } else {
                        System.err.println("empty input !");
                    }
                } catch (Throwable e) {
                    System.err.println(e.getMessage());
                }
                newEval = true;
                expression = "";
            }
        };
        final Future<?> future = executor.submit(repl);

        // gracefully shutdown jvm
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            //shutdown repl thread
            future.cancel(true);
            System.out.println();
            System.out.println();
            System.out.println("Moriturus te saluto.");
        }));
    }

    private static boolean inputNotFinished(String str) {
        int startCharCount = 0, endCharCount = 0;
        for (char c : str.toCharArray()) {
            if (c == '(')
                startCharCount++;
            if (c == ')')
                endCharCount++;
        }
        return startCharCount > endCharCount;
    }

    private Application() {
        throw new UnsupportedOperationException("illegal constructor for Application class");
    }
}
