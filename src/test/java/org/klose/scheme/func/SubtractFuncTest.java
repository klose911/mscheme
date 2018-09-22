package org.klose.scheme.func;

import org.junit.Test;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.exception.WrongArgumentTypeException;
import org.klose.scheme.type.SNumber;

import static junit.framework.Assert.assertEquals;
import static org.klose.scheme.primitive.SubtractFunc.subtract;

public class SubtractFuncTest {
    @Test
    public void subDouble() throws WrongArgumentNumberException, WrongArgumentTypeException {
        SNumber s = subtract(new SNumber(1000.0d), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        assertEquals(889d, s.getValue());
    }

    @Test
    public void subFloat() throws WrongArgumentNumberException, WrongArgumentTypeException {
        SNumber s = subtract(new SNumber(1000.0f), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        assertEquals(889f, s.getValue());
    }

    @Test
    public void subLong() throws WrongArgumentNumberException, WrongArgumentTypeException {
        SNumber s = subtract(new SNumber(1000L), new SNumber(10),
                new SNumber(100L), new SNumber(1));
        assertEquals(889L, s.getValue());
    }

    @Test
    public void subInteger() throws WrongArgumentNumberException, WrongArgumentTypeException {
        SNumber s = subtract(new SNumber(1000), new SNumber(10),
                new SNumber(100), new SNumber(1));
        assertEquals(889, s.getValue());
    }
}
