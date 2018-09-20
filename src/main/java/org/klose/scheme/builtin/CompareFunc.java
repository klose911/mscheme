package org.klose.scheme.builtin;

import org.klose.scheme.type.SBoolean;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;

public class CompareFunc {

    public static SBoolean greater(SObject... args) {
        return new SBoolean(((SNumber)args[0]).getValue().doubleValue()
                > ((SNumber)args[1]).getValue().doubleValue());
    }

    public static SBoolean less(SObject... args) {
        return new SBoolean(((SNumber)args[0]).getValue().doubleValue()
                < ((SNumber)args[1]).getValue().doubleValue());
    }

    public static SBoolean equals(SObject... args) {
        return new SBoolean(((SNumber)args[0]).getValue().doubleValue()
                == ((SNumber)args[1]).getValue().doubleValue());
    }
}
