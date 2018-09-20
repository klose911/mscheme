package org.klose.scheme.func;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.builtin.MultipleFunc;
import org.klose.scheme.type.SNumber;

public class MultipleFuncTest {
    @Test
    public void multipleDouble() {
        SNumber s = MultipleFunc.multiple(new SNumber(0.1d), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        Assert.assertEquals(100.0d, s.getValue());
    }

    @Test
    public void multipleFloat() {
        SNumber s = MultipleFunc.multiple(new SNumber(0.1f), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        Assert.assertEquals(100.00f, s.getValue());
    }

    @Test
    public void multipleLong() {
        SNumber s = MultipleFunc.multiple(new SNumber(-1L), new SNumber(10),
                new SNumber(100L), new SNumber(10L));
        Assert.assertEquals(-10000L, s.getValue());
    }

    @Test
    public void multipleInt() {
        SNumber s = MultipleFunc.multiple(new SNumber(-1), new SNumber(10),
                new SNumber(100), new SNumber(10));
        Assert.assertEquals(-10000, s.getValue());
    }
}
