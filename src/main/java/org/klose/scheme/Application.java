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
        SFrame rootEnv = InitEnv.init();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        //repl: loop of read -> eval -> print -> read
        final Runnable repl = () -> {
            String src;
            while (true) {
              System.out.println("scheme>>");
                try {
                    src = console.readLine();
                    if (src == null)
                        break;

                    if (!StringUtils.isEmpty(src.trim())) {
                        SExpression exp = SParser.parse(src);
                        SObject val = EvalService.eval(exp, rootEnv);
                        System.out.println(val);
                    } else {
                        System.err.println("empty input !");
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
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

    private Application() {
        throw new UnsupportedOperationException("illegal constructor for Application class");
    }
}

