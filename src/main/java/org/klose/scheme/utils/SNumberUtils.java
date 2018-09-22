package org.klose.scheme.utils;

import org.klose.scheme.exception.WrongArgumentTypeException;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;

public class SNumberUtils {

    public static boolean hasDouble(SNumber[] args) {
        if (args == null)
            throw new IllegalArgumentException("args can not be null");

        for (SNumber n : args) {
            if (n.getValue() instanceof Double)
                return true;
        }
        return false;
    }

    public static boolean hasFloat(SNumber[] args) {
        if (args == null)
            throw new IllegalArgumentException("args can not be null");

        for (SNumber n : args) {
            if (n.getValue() instanceof Float)
                return true;
        }
        return false;
    }

    public static boolean hasLong(SNumber[] args) {
        if (args == null)
            throw new IllegalArgumentException("args can not be null");

        for (SNumber n : args) {
            if (n.getValue() instanceof Long)
                return true;
        }
        return false;
    }

    public static SNumber[] convert(SObject[] args) throws WrongArgumentTypeException {
        if (args == null)
            throw new IllegalArgumentException("args can not be null");

        final int length = args.length;
        SNumber[] a = new SNumber[length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null)
                throw new IllegalArgumentException("arg can not be null");
            if (!(args[i] instanceof SNumber))
                throw new WrongArgumentTypeException(SNumber.class, args[i].getClass(), "convert");

            a[i] = (SNumber) args[i];
        }
        return a;
    }
}
