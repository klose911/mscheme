package org.klose.scheme.builtin;

import org.klose.scheme.type.SNumber;
import org.klose.scheme.utils.NumberUtils;

public class AddFunc {
    public static SNumber add(SNumber... args) {
        Number result;
        if (NumberUtils.hasDouble(args)) {
            result = addDouble(args);
        } else if (NumberUtils.hasFloat(args)) {
            result = addFloat(args);
        } else if (NumberUtils.hasLong(args)) {
            result = addLong(args);
        } else
            result = addInteger(args);

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
