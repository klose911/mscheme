package org.klose.scheme.utils;

import org.klose.scheme.type.SNumber;

public class NumberUtils {

    public static boolean hasDouble(SNumber[] args) {
        for (SNumber n : args) {
            if (n.getValue() instanceof Double)
                return true;
        }
        return false;
    }

    public static boolean hasFloat(SNumber... args) {
        for (SNumber n : args) {
            if (n.getValue() instanceof Float)
                return true;
        }
        return false;
    }

    public static boolean hasLong(SNumber[] args) {
        for (SNumber n : args) {
            if (n.getValue() instanceof Long)
                return true;
        }
        return false;
    }
}
