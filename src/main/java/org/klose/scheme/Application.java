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
            String src;
            while (true) {
                System.out.print(">> ");
                try {
                    src = console.readLine();
                    if (!StringUtils.isEmpty(src.trim())) {
                        SExpression exp = SParser.parse(src);
                        SObject val = EvalService.eval(exp, rootEnv);
                        if (val != null) {
                            System.out.println(val);
                        }
                    } else {
                        System.err.println("empty input !");
                    }
                } catch (Throwable e) {
                    System.err.println(e.getMessage());
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
            System.out.println("Have Fun, Bye Bye... ");
        }));
    }
}
