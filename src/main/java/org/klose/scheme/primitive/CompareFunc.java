package org.klose.scheme.primitive;

import org.klose.scheme.type.SBoolean;
import org.klose.scheme.type.SObject;

public class CompareFunc {

    public static SBoolean greater(SObject... args) {
        Number args0 = (Number) args[0].getValue();
        Number args1 = (Number) args[1].getValue();
        return new SBoolean(args0.doubleValue() > args1.doubleValue());
    }

    public static SBoolean less(SObject... args) {
        Number args0 = (Number) args[0].getValue();
        Number args1 = (Number) args[1].getValue();
        return new SBoolean(args0.doubleValue() < args1.doubleValue());
    }

    public static SBoolean eq(SObject... args) {
        Number args0 = (Number) args[0].getValue();
        Number args1 = (Number) args[1].getValue();
        return new SBoolean(args0.doubleValue() == args1.doubleValue());
    }
}
