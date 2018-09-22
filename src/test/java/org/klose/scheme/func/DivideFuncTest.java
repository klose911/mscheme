package org.klose.scheme.func;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.exception.WrongArgumentTypeException;
import org.klose.scheme.type.SNumber;

import static junit.framework.Assert.assertEquals;
import static org.klose.scheme.primitive.DivideFunc.divide;

public class DivideFuncTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void integerDivide() throws IllegalExpressionException, WrongArgumentTypeException, WrongArgumentNumberException {
        SNumber result = divide(new SNumber(1000), new SNumber(2), new SNumber(5));
        assertEquals(100.00d, result.getValue());
    }

    @Test
    public void divideByZero() throws IllegalExpressionException, WrongArgumentTypeException, WrongArgumentNumberException {
        exceptionRule.expect(IllegalExpressionException.class);
        exceptionRule.expectMessage("divide operand can not be zero");
        divide(new SNumber(1000d), new SNumber(0.0f), new SNumber(5));
    }
}
