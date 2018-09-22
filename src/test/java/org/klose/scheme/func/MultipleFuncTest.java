package org.klose.scheme.func;

import org.junit.Test;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.exception.WrongArgumentTypeException;
import org.klose.scheme.type.SNumber;

import static junit.framework.Assert.assertEquals;
import static org.klose.scheme.primitive.MultipleFunc.multiple;

public class MultipleFuncTest {
    @Test
    public void multipleDouble() throws WrongArgumentTypeException, WrongArgumentNumberException {
        SNumber s = multiple(new SNumber(0.1d), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        assertEquals(100.0d, s.getValue());
    }

    @Test
    public void multipleFloat() throws WrongArgumentTypeException, WrongArgumentNumberException {
        SNumber s = multiple(new SNumber(0.1f), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        assertEquals(100.00f, s.getValue());
    }

    @Test
    public void multipleLong() throws WrongArgumentTypeException, WrongArgumentNumberException {
        SNumber s = multiple(new SNumber(-1L), new SNumber(10),
                new SNumber(100L), new SNumber(10L));
        assertEquals(-10000L, s.getValue());
    }

    @Test
    public void multipleInt() throws WrongArgumentTypeException, WrongArgumentNumberException {
        SNumber s = multiple(new SNumber(-1), new SNumber(10),
                new SNumber(100), new SNumber(10));
        assertEquals(-10000, s.getValue());
    }
}
