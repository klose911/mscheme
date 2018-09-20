package org.klose.scheme.builtin;

import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.utils.SNumberUtils;

public class AddFunc {
    public static SNumber add(SObject... args) {
        Number result;
        SNumber[] args1 = new SNumber[args.length];
        for(int i = 0; i<args.length; i++) {
            args1[i] = (SNumber) args[i];
        }
        if (SNumberUtils.hasDouble(args1)) {
            result = addDouble(args1);
        } else if (SNumberUtils.hasFloat(args1)) {
            result = addFloat(args1);
        } else if (SNumberUtils.hasLong(args1)) {
            result = addLong(args1);
        } else
            result = addInteger(args1);

        return new SNumber(result);
    }

    private static double addDouble(SNumber... args) {
        double result = 0d;
        for (SNumber s : args)
            result += s.getValue().doubleValue();

        return result;
    }

    private static Float addFloat(SNumber... args) {
        float result = 0f;
        for (SNumber s : args)
            result += s.getValue().floatValue();

        return result;
    }

    private static Long addLong(SNumber... args) {
        long result = 0L;
        for (SNumber s : args)
            result += s.getValue().longValue();

        return result;
    }

    private static Integer addInteger(SNumber... args) {
        Integer result = 0;
        for (SNumber s : args)
            result += s.getValue().intValue();

        return result;
    }
}
