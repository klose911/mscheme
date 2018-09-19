package org.klose.scheme.builtin;

import org.klose.scheme.type.SNumber;
import org.klose.scheme.utils.NumberUtils;

public class MultipleFunc {
    public static SNumber multiple(SNumber... args) {
        Number result;
        if (NumberUtils.hasDouble(args)) {
            result = multipleDouble(args);
        } else if (NumberUtils.hasFloat(args)) {
            result = multipleFloat(args);
        } else if (NumberUtils.hasLong(args)) {
            result = multipleLong(args);
        } else
            result = multipleInteger(args);

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
