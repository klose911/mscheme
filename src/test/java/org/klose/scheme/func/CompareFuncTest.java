package org.klose.scheme.func;

import org.junit.Test;
import org.klose.scheme.type.SNumber;

import static junit.framework.Assert.assertTrue;
import static org.klose.scheme.primitive.CompareFunc.*;

public class CompareFuncTest {

    @Test
    public void equalsCompare() {
        assertTrue(eq(new SNumber(1000.0d),
                new SNumber(1000.0d)).getValue());
        assertTrue(eq(new SNumber(1000.0d),
                new SNumber(1000.0f)).getValue());
        assertTrue(eq(new SNumber(1000.0d),
                new SNumber(1000L)).getValue());
        assertTrue(eq(new SNumber(1000.0d),
                new SNumber(1000)).getValue());
        assertTrue(eq(new SNumber(1000.0f),
                new SNumber(1000.0d)).getValue());
        assertTrue(eq(new SNumber(1000.0f),
                new SNumber(1000L)).getValue());
        assertTrue(eq(new SNumber(1000.0f),
                new SNumber(1000)).getValue());
        assertTrue(eq(new SNumber(1000L),
                new SNumber(1000L)).getValue());
        assertTrue(eq(new SNumber(1000L),
                new SNumber(1000)).getValue());
        assertTrue(eq(new SNumber(1000),
                new SNumber(1000)).getValue());
    }

    @Test
    public void greaterCompare() {
        assertTrue(greater(new SNumber(1000.1d),
                new SNumber(1000.0d)).getValue());
        assertTrue(greater(new SNumber(1000.1d),
                new SNumber(1000.0f)).getValue());
        assertTrue(greater(new SNumber(1000.1d),
                new SNumber(1000L)).getValue());
        assertTrue(greater(new SNumber(1000.1d),
                new SNumber(1000)).getValue());
        assertTrue(greater(new SNumber(1000.1f),
                new SNumber(1000.0d)).getValue());
        assertTrue(greater(new SNumber(1000.1f),
                new SNumber(1000L)).getValue());
        assertTrue(greater(new SNumber(1000.1f),
                new SNumber(1000)).getValue());
        assertTrue(greater(new SNumber(1001L),
                new SNumber(1000L)).getValue());
        assertTrue(greater(new SNumber(1001L),
                new SNumber(1000)).getValue());
        assertTrue(greater(new SNumber(1001),
                new SNumber(1000)).getValue());
    }

    @Test
    public void lessCompare() {
        assertTrue(less(new SNumber(1000.0d),
                new SNumber(1000.1d)).getValue());
        assertTrue(less(new SNumber(1000.0d),
                new SNumber(1000.1f)).getValue());
        assertTrue(less(new SNumber(1000.0d),
                new SNumber(1001L)).getValue());
        assertTrue(less(new SNumber(1000.0d),
                new SNumber(1001)).getValue());
        assertTrue(less(new SNumber(1000.0f),
                new SNumber(1000.1d)).getValue());
        assertTrue(less(new SNumber(1000.0f),
                new SNumber(1001L)).getValue());
        assertTrue(less(new SNumber(1000.0f),
                new SNumber(1001)).getValue());
        assertTrue(less(new SNumber(1000L),
                new SNumber(1001L)).getValue());
        assertTrue(less(new SNumber(1000L),
                new SNumber(1001)).getValue());
        assertTrue(less(new SNumber(1000),
                new SNumber(1001)).getValue());
    }
}
