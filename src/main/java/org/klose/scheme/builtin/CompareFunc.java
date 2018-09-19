package org.klose.scheme.builtin;

import org.klose.scheme.type.SBoolean;
import org.klose.scheme.type.SNumber;

public class CompareFunc {

    public static SBoolean greater(SNumber a, SNumber b) {
        return new SBoolean(a.getValue().doubleValue() > b.getValue().doubleValue());
    }

    public static SBoolean less(SNumber a, SNumber b) {
        return new SBoolean(a.getValue().doubleValue() < b.getValue().doubleValue());
    }

    public static SBoolean equals(SNumber a, SNumber b) {
        return new SBoolean(a.getValue().doubleValue() == b.getValue().doubleValue());
    }
}
