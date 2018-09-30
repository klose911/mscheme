package org.klose.scheme.primitive;

import org.klose.scheme.type.SObject;

import static org.klose.scheme.constant.SConstant.NIL;

public class PrintFunc {

    public static SObject print(SObject... args) {
        for (int i = 0; i < args.length; i++)
            System.out.println(args[i]);

        return null;
    }

    private PrintFunc() {
        throw new UnsupportedOperationException("illegal constructor for PrintFunc");
    }
}
