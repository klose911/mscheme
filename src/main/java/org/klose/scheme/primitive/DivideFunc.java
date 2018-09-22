package org.klose.scheme.primitive;

import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.exception.WrongArgumentTypeException;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.utils.SNumberUtils;

public class DivideFunc {

    public static SNumber divide(SNumber... args)
            throws WrongArgumentNumberException, WrongArgumentTypeException, IllegalExpressionException {
        if (args == null)
            throw new IllegalArgumentException("args can not be null");
        if (args.length < 2)
            throw new WrongArgumentNumberException(2, args.length, "/");

        SNumber[] numbers;
        try {
            numbers = SNumberUtils.convert(args);
        } catch (WrongArgumentTypeException e) {
            throw new WrongArgumentTypeException(e.getExpected(), e.getActual(), "/");
        }
        double result = numbers[0].getValue().doubleValue();
        double operand;
        for (int i = 1; i < numbers.length; i++) {
            operand = numbers[i].getValue().doubleValue();
            if (operand == 0.0d) {
                throw new IllegalExpressionException("divide operand can not be zero");
            }
            result /= operand;
        }
        return new SNumber(result);
    }
}
