package org.klose.scheme.primitive;

import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.exception.WrongArgumentTypeException;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.utils.SNumberUtils;

public class MultipleFunc {
    public static SNumber multiple(SObject... args) throws WrongArgumentTypeException, WrongArgumentNumberException {
        if (args == null)
            throw new IllegalArgumentException("args can not be null");
        if (args.length < 2)
            throw new WrongArgumentNumberException(2, args.length, "*");

        Number result;
        SNumber[] numbers;
        try {
            numbers = SNumberUtils.convert(args);
        } catch (WrongArgumentTypeException e) {
            throw new WrongArgumentTypeException(e.getExpected(), e.getActual(), "*");
        }

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
        assert (args != null && args.length > 1);

        double result = args[0].getValue().doubleValue();
        for (int i = 1; i < args.length; i++)
            result *= args[i].getValue().doubleValue();

        return result;
    }

    private static Float multipleFloat(SNumber... args) {
        assert (args != null && args.length > 1);

        float result = args[0].getValue().floatValue();
        for (int i = 1; i < args.length; i++)
            result *= args[i].getValue().floatValue();

        return result;
    }

    private static Long multipleLong(SNumber... args) {
        assert (args != null && args.length > 1);

        long result = args[0].getValue().longValue();
        for (int i = 1; i < args.length; i++)
            result *= args[i].getValue().longValue();

        return result;
    }

    private static Integer multipleInteger(SNumber... args) {
        assert (args != null && args.length > 1);

        int result = args[0].getValue().intValue();
        for (int i = 1; i < args.length; i++)
            result *= args[i].getValue().intValue();

        return result;
    }
}
