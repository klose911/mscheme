package org.klose.scheme.builtin;

import org.klose.scheme.type.SBoolean;
import org.klose.scheme.type.SObject;

public class BooleanFunc {
    public static SBoolean and(SObject... args) {
        SBoolean args0 = (SBoolean) args[0];
        SBoolean args1 = (SBoolean) args[1];
        return new SBoolean(args0.getValue() && args1.getValue());
    }

    public static SBoolean or(SObject... args) {
        SBoolean args0 = (SBoolean) args[0];
        SBoolean args1 = (SBoolean) args[1];
        return new SBoolean(args0.getValue() || args1.getValue());
    }

    public static SBoolean not(SObject... args) {
        SBoolean args0 = (SBoolean) args[0];
        return new SBoolean(!args0.getValue());
    }

    public static SBoolean equalsTo(SObject... args) {
        return new SBoolean(args[0].toString().equals(args[1].toString()));
    }
}
