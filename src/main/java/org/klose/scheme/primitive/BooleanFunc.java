package org.klose.scheme.primitive;

import org.klose.scheme.type.SBoolean;
import org.klose.scheme.type.SObject;

public class BooleanFunc {
    public static SBoolean and(SObject... args) {
        return new SBoolean((Boolean) args[0].getValue() && (Boolean) args[1].getValue());
    }

    public static SBoolean or(SObject... args) {
        return new SBoolean((Boolean) args[0].getValue() || (Boolean) args[1].getValue());
    }

    public static SBoolean not(SObject... args) {
        return new SBoolean(!(Boolean) args[0].getValue());
    }

    public static SBoolean eq(SObject... args) {
        return new SBoolean(args[0].getValue().equals(args[1].getValue()));
    }
}
