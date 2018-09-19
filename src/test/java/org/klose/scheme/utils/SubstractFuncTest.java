package org.klose.scheme.utils;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.builtin.SubtractFunc;
import org.klose.scheme.type.SNumber;

public class SubstractFuncTest {
    @Test
    public void subDouble() {
        SNumber s = SubtractFunc.subtract(new SNumber(1000.0d), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        Assert.assertEquals(889d, s.getValue());
    }

    @Test
    public void subFloat() {
        SNumber s = SubtractFunc.subtract(new SNumber(1000.0f), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        Assert.assertEquals(889f, s.getValue());
    }

    @Test
    public void subLong() {
        SNumber s = SubtractFunc.subtract(new SNumber(1000L), new SNumber(10),
                new SNumber(100L), new SNumber(1));
        Assert.assertEquals(889L, s.getValue());
    }

    @Test
    public void subInteger() {
        SNumber s = SubtractFunc.subtract(new SNumber(1000), new SNumber(10),
                new SNumber(100), new SNumber(1));
        Assert.assertEquals(889, s.getValue());
    }
}
