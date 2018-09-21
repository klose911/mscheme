package org.klose.scheme.utils;

import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;

public class SNumberUtils {

    public static boolean hasDouble(SNumber[] args) {
        for (SNumber n : args) {
            if (n.getValue() instanceof Double)
                return true;
        }
        return false;
    }

    public static boolean hasFloat(SNumber[] args) {
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

    public static SNumber[] convert(SObject[] args) {
        final int length = args.length;
        SNumber[] a = new SNumber[length];
        for (int i = 0; i < args.length; i++)
            a[i] = (SNumber) args[i];
        return a;
    }
}
