package org.klose.scheme.func;

import org.junit.Test;
import org.klose.scheme.type.SNumber;

import static junit.framework.Assert.assertEquals;
import static org.klose.scheme.builtin.SubtractFunc.subtract;

public class SubstractFuncTest {
    @Test
    public void subDouble() {
        SNumber s = subtract(new SNumber(1000.0d), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        assertEquals(889d, s.getValue());
    }

    @Test
    public void subFloat() {
        SNumber s = subtract(new SNumber(1000.0f), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        assertEquals(889f, s.getValue());
    }

    @Test
    public void subLong() {
        SNumber s = subtract(new SNumber(1000L), new SNumber(10),
                new SNumber(100L), new SNumber(1));
        assertEquals(889L, s.getValue());
    }

    @Test
    public void subInteger() {
        SNumber s = subtract(new SNumber(1000), new SNumber(10),
                new SNumber(100), new SNumber(1));
        assertEquals(889, s.getValue());
    }
}
