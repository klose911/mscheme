package org.klose.scheme.utils;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.builtin.DivideFunc;
import org.klose.scheme.type.SNumber;

public class DivideFuncTest {

    @Test
    public void divid() {
        SNumber result = DivideFunc.divide(new SNumber(1000), new SNumber(2), new SNumber(5));
        Assert.assertEquals(100.00d, result.getValue());
    }

    @Test
    public void dividWithZero() {
        try {
            SNumber result = DivideFunc.divide(new SNumber(1000d), new SNumber(0.0f), new SNumber(5));
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("divide operand can not be zero", e.getMessage());
            System.out.println("caught exception");
        }
    }
}
