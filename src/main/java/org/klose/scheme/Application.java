package org.klose.scheme;


import org.klose.scheme.model.SEnvironment;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.service.EvalService;
import org.klose.scheme.type.SObject;
import org.klose.scheme.utils.InitEnv;
import org.klose.scheme.utils.SParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Application {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        SEnvironment rootEnv = InitEnv.init();
        final Runnable repl = () -> {
            String src;
            while (true) {
                System.out.print(">> ");
                try {
                    src = console.readLine();
                    if (!"".equals(src.trim())) {
                        SExpression exp = SParser.parse(src);
                        SObject val = EvalService.eval(exp, rootEnv);
                        if (val != null) {
                            System.out.println(val);
                        }
                    } else {
                        System.err.println("empty cmd !");
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        };
        final Future<?> future = executor.submit(repl);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            future.cancel(true);
            System.out.println();
            System.out.println();
            System.out.println("Have Fun, Bye Bye... ");
        }));
    }
}
