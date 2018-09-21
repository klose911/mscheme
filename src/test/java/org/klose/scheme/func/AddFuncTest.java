package org.klose.scheme.func;

import org.junit.Test;
import org.klose.scheme.type.SNumber;

import static junit.framework.Assert.assertEquals;
import static org.klose.scheme.primitive.AddFunc.add;

public class AddFuncTest {
    @Test
    public void addDouble() {
        SNumber s = add(new SNumber(0.1d), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        assertEquals(111.1d, s.getValue());
    }

    @Test
    public void addFloat() {
        SNumber s = add(new SNumber(0.1f), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        assertEquals(111.1f, s.getValue());
    }

    @Test
    public void addLong() {
        SNumber s = add(new SNumber(1L), new SNumber(10),
                new SNumber(100L), new SNumber(1000));
        assertEquals(1111L, s.getValue());
    }

    @Test
    public void addInteger() {
        SNumber s = add(new SNumber(1), new SNumber(10),
                new SNumber(100), new SNumber(1000));
        assertEquals(1111, s.getValue());
    }
}
