package org.klose.scheme.builtin;

import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.utils.SNumberUtils;

public class MultipleFunc {
    public static SNumber multiple(SObject... args) {
        Number result;
        SNumber[] numbers = SNumberUtils.convert(args);
        if (SNumberUtils.hasDouble(numbers)) {
            result = multipleDouble(numbers);
        } else if (SNumberUtils.hasFloat(numbers)) {
            result = multipleFloat(numbers);
        } else if (SNumberUtils.hasLong(numbers)) {
            result = multipleLong(numbers);
        } else
            result = multipleInteger(numbers);

        return new SNumber(result);
    }

    private static double multipleDouble(SNumber... args) {
        double result = args[0].getValue().doubleValue();
        for (int i = 1; i < args.length; i++)
            result *= args[i].getValue().doubleValue();

        return result;
    }

    private static Float multipleFloat(SNumber... args) {
        float result = args[0].getValue().floatValue();
        for (int i = 1; i < args.length; i++)
            result *= args[i].getValue().floatValue();

        return result;
    }

    private static Long multipleLong(SNumber... args) {
        long result = args[0].getValue().longValue();
        for (int i = 1; i < args.length; i++)
            result *= args[i].getValue().longValue();

        return result;
    }

    private static Integer multipleInteger(SNumber... args) {
        int result = args[0].getValue().intValue();
        for (int i = 1; i < args.length; i++)
            result *= args[i].getValue().intValue();

        return result;
    }
}
