package org.klose.scheme.func;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.klose.scheme.type.SNumber;

import static junit.framework.Assert.assertEquals;
import static org.klose.scheme.primitive.DivideFunc.divide;

public class DivideFuncTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void integerDivide() {
        SNumber result = divide(new SNumber(1000), new SNumber(2), new SNumber(5));
        assertEquals(100.00d, result.getValue());
    }

    @Test
    public void divideByZero() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("divide operand can not be zero");
        divide(new SNumber(1000d), new SNumber(0.0f), new SNumber(5));
    }
}
