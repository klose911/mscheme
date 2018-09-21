package org.klose.scheme.primitive;

import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.utils.SNumberUtils;

public class AddFunc {
    public static SNumber add(SObject... args) {
        Number result;
        SNumber[] numbers = SNumberUtils.convert(args);
        if (SNumberUtils.hasDouble(numbers)) {
            result = addDouble(numbers);
        } else if (SNumberUtils.hasFloat(numbers)) {
            result = addFloat(numbers);
        } else if (SNumberUtils.hasLong(numbers)) {
            result = addLong(numbers);
        } else
            result = addInteger(numbers);

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
