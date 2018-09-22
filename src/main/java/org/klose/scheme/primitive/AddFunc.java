package org.klose.scheme.primitive;

import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.exception.WrongArgumentTypeException;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.utils.SNumberUtils;

public class AddFunc {
    public static SNumber add(SObject... args)
            throws WrongArgumentTypeException, WrongArgumentNumberException {
        if (args == null)
            throw new IllegalArgumentException("args can not be null");
        if (args.length < 2)
            throw new WrongArgumentNumberException(2, args.length, "+");

        SNumber[] numbers;
        try {
            numbers = SNumberUtils.convert(args);
        } catch (WrongArgumentTypeException e) {
            throw new WrongArgumentTypeException(e.getExpected(), e.getActual(), "+");
        }
        Number result;

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
        assert (args != null && args.length > 1);

        double result = 0d;
        for (SNumber s : args)
            result += s.getValue().doubleValue();

        return result;
    }

    private static Float addFloat(SNumber... args) {
        assert (args != null && args.length > 1);

        float result = 0f;
        for (SNumber s : args)
            result += s.getValue().floatValue();

        return result;
    }

    private static Long addLong(SNumber... args) {
        assert (args != null && args.length > 1);

        long result = 0L;
        for (SNumber s : args)
            result += s.getValue().longValue();

        return result;
    }

    private static Integer addInteger(SNumber... args) {
        assert (args != null && args.length > 1);

        int result = 0;
        for (SNumber s : args)
            result += s.getValue().intValue();

        return result;
    }
}
