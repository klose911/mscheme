package org.klose.scheme.primitive;

import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.utils.SNumberUtils;

public class SubtractFunc {
    public static SNumber subtract(SObject... args) {
        Number result;
        SNumber[] numbers = SNumberUtils.convert(args);
        if (SNumberUtils.hasDouble(numbers)) {
            result = subtractDouble(numbers);
        } else if (SNumberUtils.hasFloat(numbers)) {
            result = subtractFloat(numbers);
        } else if (SNumberUtils.hasLong(numbers)) {
            result = subtractLong(numbers);
        } else
            result = subtractInteger(numbers);

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
