package org.klose.scheme.builtin;

import org.klose.scheme.type.SNumber;
import org.klose.scheme.utils.SNumberUtils;

public class SubtractFunc {
    public static SNumber subtract(SNumber... args) {
        Number result;
        if (SNumberUtils.hasDouble(args)) {
            result = subtractDouble(args);
        } else if (SNumberUtils.hasFloat(args)) {
            result = subtractFloat(args);
        } else if (SNumberUtils.hasLong(args)) {
            result = subtractLong(args);
        } else
            result = subtractInteger(args);

        return new SNumber(result);
    }

    private static double subtractDouble(SNumber... args) {
        double result = args[0].getValue().doubleValue();
        for (int i = 1; i < args.length; i++)
            result -= args[i].getValue().doubleValue();

        return result;
    }

    private static Float subtractFloat(SNumber... args) {
        float result = args[0].getValue().floatValue();
        for (int i = 1; i < args.length; i++)
            result -= args[i].getValue().floatValue();

        return result;
    }

    private static Long subtractLong(SNumber... args) {
        long result = args[0].getValue().longValue();
        for (int i = 1; i < args.length; i++)
            result -= args[i].getValue().longValue();

        return result;
    }

    private static Integer subtractInteger(SNumber... args) {
        Integer result = args[0].getValue().intValue();
        for (int i = 1; i < args.length; i++)
            result -= args[i].getValue().intValue();

        return result;
    }
}
