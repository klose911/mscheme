package org.klose.scheme.primitive;

import org.klose.scheme.type.SNumber;
import org.klose.scheme.utils.SNumberUtils;

public class DivideFunc {

    public static SNumber divide(SNumber... args) {
        SNumber[] numbers = SNumberUtils.convert(args);
        double result = numbers[0].getValue().doubleValue();
        double operand;
        for (int i = 1; i < numbers.length; i++) {
            operand = numbers[i].getValue().doubleValue();
            if (operand == 0.0d) {
                throw new IllegalArgumentException("divide operand can not be zero");
            }
            result /= operand;
        }
        return new SNumber(result);
    }
}
