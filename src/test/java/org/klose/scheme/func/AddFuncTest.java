package org.klose.scheme.func;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.builtin.AddFunc;
import org.klose.scheme.type.SNumber;

public class AddFuncTest {
    @Test
    public void addDouble() {
        SNumber s = AddFunc.add(new SNumber(0.1d), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        Assert.assertEquals(111.1d, s.getValue());
    }

    @Test
    public void addFloat() {
        SNumber s = AddFunc.add(new SNumber(0.1f), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        Assert.assertEquals(111.1f, s.getValue());
    }

    @Test
    public void addLong() {
        SNumber s = AddFunc.add(new SNumber(1L), new SNumber(10),
                new SNumber(100L), new SNumber(1000));
        Assert.assertEquals(1111L, s.getValue());
    }

    @Test
    public void addInteger() {
        SNumber s = AddFunc.add(new SNumber(1), new SNumber(10),
                new SNumber(100), new SNumber(1000));
        Assert.assertEquals(1111, s.getValue());
    }
}
