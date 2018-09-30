package org.klose.scheme.primitive;

import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.exception.WrongArgumentTypeException;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.utils.SNumberUtils;

public class SubtractFunc {
    public static SNumber subtract(SObject... args)
            throws WrongArgumentNumberException, WrongArgumentTypeException {
        if (args == null)
            throw new IllegalArgumentException("args can not be null");
        if (args.length < 2)
            throw new WrongArgumentNumberException(2, args.length, "-");

        SNumber[] numbers;
        try {
            numbers = SNumberUtils.convert(args);
        } catch (WrongArgumentTypeException e) {
            throw new WrongArgumentTypeException(e.getExpected(), e.getActual(), "-");
        }

        Number result;
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
        assert (args != null && args.length > 1);

        double result = args[0].getValue().doubleValue();
        for (int i = 1; i < args.length; i++)
            result -= args[i].getValue().doubleValue();

        return result;
    }

    private static Float subtractFloat(SNumber... args) {
        assert (args != null && args.length > 1);

        float result = args[0].getValue().floatValue();
        for (int i = 1; i < args.length; i++)
            result -= args[i].getValue().floatValue();

        return result;
    }

    private static Long subtractLong(SNumber... args) {
        assert (args != null && args.length > 1);

        long result = args[0].getValue().longValue();
        for (int i = 1; i < args.length; i++)
            result -= args[i].getValue().longValue();

        return result;
    }

    private static Integer subtractInteger(SNumber... args) {
        assert (args != null && args.length > 1);

        int result = args[0].getValue().intValue();
        for (int i = 1; i < args.length; i++)
            result -= args[i].getValue().intValue();

        return result;
    }

    private SubtractFunc() {
        throw new UnsupportedOperationException("illeagl constructor for SubtractFunc");
    }
}
