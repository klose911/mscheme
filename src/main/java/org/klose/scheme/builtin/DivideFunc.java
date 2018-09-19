package org.klose.scheme.builtin;

import org.klose.scheme.type.SNumber;

public class DivideFunc {

    public static SNumber divide(SNumber... args) {
        double result = args[0].getValue().doubleValue();
        double operand;
        for (int i = 1; i < args.length; i++) {
            operand = args[i].getValue().doubleValue();
            if (operand == 0.0d) {
                throw new IllegalArgumentException("divide operand can not be zero");
            }
            result /= operand;
        }
        return new SNumber(result);
    }
}
