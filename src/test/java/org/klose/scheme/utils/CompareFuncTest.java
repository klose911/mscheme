package org.klose.scheme.utils;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.builtin.CompareFunc;
import org.klose.scheme.type.SNumber;

public class CompareFuncTest {

    @Test
    public void equals() {
        Assert.assertTrue(CompareFunc.equals(new SNumber(1000.0d),
                new SNumber(1000.0d)).getValue());
        Assert.assertTrue(CompareFunc.equals(new SNumber(1000.0d),
                new SNumber(1000.0f)).getValue());
        Assert.assertTrue(CompareFunc.equals(new SNumber(1000.0d),
                new SNumber(1000L)).getValue());
        Assert.assertTrue(CompareFunc.equals(new SNumber(1000.0d),
                new SNumber(1000)).getValue());
        Assert.assertTrue(CompareFunc.equals(new SNumber(1000.0f),
                new SNumber(1000.0d)).getValue());
        Assert.assertTrue(CompareFunc.equals(new SNumber(1000.0f),
                new SNumber(1000L)).getValue());
        Assert.assertTrue(CompareFunc.equals(new SNumber(1000.0f),
                new SNumber(1000)).getValue());
        Assert.assertTrue(CompareFunc.equals(new SNumber(1000L),
                new SNumber(1000L)).getValue());
        Assert.assertTrue(CompareFunc.equals(new SNumber(1000L),
                new SNumber(1000)).getValue());
        Assert.assertTrue(CompareFunc.equals(new SNumber(1000),
                new SNumber(1000)).getValue());
    }

    @Test
    public void greater() {
        Assert.assertTrue(CompareFunc.greater(new SNumber(1000.1d),
                new SNumber(1000.0d)).getValue());
        Assert.assertTrue(CompareFunc.greater(new SNumber(1000.1d),
                new SNumber(1000.0f)).getValue());
        Assert.assertTrue(CompareFunc.greater(new SNumber(1000.1d),
                new SNumber(1000L)).getValue());
        Assert.assertTrue(CompareFunc.greater(new SNumber(1000.1d),
                new SNumber(1000)).getValue());
        Assert.assertTrue(CompareFunc.greater(new SNumber(1000.1f),
                new SNumber(1000.0d)).getValue());
        Assert.assertTrue(CompareFunc.greater(new SNumber(1000.1f),
                new SNumber(1000L)).getValue());
        Assert.assertTrue(CompareFunc.greater(new SNumber(1000.1f),
                new SNumber(1000)).getValue());
        Assert.assertTrue(CompareFunc.greater(new SNumber(1001L),
                new SNumber(1000L)).getValue());
        Assert.assertTrue(CompareFunc.greater(new SNumber(1001L),
                new SNumber(1000)).getValue());
        Assert.assertTrue(CompareFunc.greater(new SNumber(1001),
                new SNumber(1000)).getValue());
    }

    @Test
    public void less() {
        Assert.assertTrue(CompareFunc.less(new SNumber(1000.0d),
                new SNumber(1000.1d)).getValue());
        Assert.assertTrue(CompareFunc.less(new SNumber(1000.0d),
                new SNumber(1000.1f)).getValue());
        Assert.assertTrue(CompareFunc.less(new SNumber(1000.0d),
                new SNumber(1001L)).getValue());
        Assert.assertTrue(CompareFunc.less(new SNumber(1000.0d),
                new SNumber(1001)).getValue());
        Assert.assertTrue(CompareFunc.less(new SNumber(1000.0f),
                new SNumber(1000.1d)).getValue());
        Assert.assertTrue(CompareFunc.less(new SNumber(1000.0f),
                new SNumber(1001L)).getValue());
        Assert.assertTrue(CompareFunc.less(new SNumber(1000.0f),
                new SNumber(1001)).getValue());
        Assert.assertTrue(CompareFunc.less(new SNumber(1000L),
                new SNumber(1001L)).getValue());
        Assert.assertTrue(CompareFunc.less(new SNumber(1000L),
                new SNumber(1001)).getValue());
        Assert.assertTrue(CompareFunc.less(new SNumber(1000),
                new SNumber(1001)).getValue());
    }
}
