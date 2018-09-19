package org.klose.scheme.utils;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.builtin.AddFunc;
import org.klose.scheme.type.SNumber;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

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

    @Test
    public void test() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Class a = Class.forName("org.klose.scheme.builtin.AddFunc");
        Method m = Arrays.stream(a.getMethods()).filter(x -> x.getName().equals("add")).findFirst().get();
        SNumber[] args = new SNumber[]{new SNumber(0.1d), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f)};
        SNumber n = (SNumber) m.invoke(null, new Object[] {args});
        System.out.println(n);

    }
}
