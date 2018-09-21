package org.klose.scheme.func;

import org.junit.Test;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SString;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.klose.scheme.constant.SConstant.FALSE;
import static org.klose.scheme.constant.SConstant.TRUE;
import static org.klose.scheme.primitive.BooleanFunc.*;

public class BooleanFuncTest {

    @Test
    public void andTest() {
        assertTrue(and(TRUE, TRUE).getValue());
        assertFalse(and(TRUE, FALSE).getValue());
        assertFalse(and(FALSE, TRUE).getValue());
        assertFalse(and(FALSE, FALSE).getValue());
    }

    @Test
    public void orTest() {
        assertTrue(or(TRUE, TRUE).getValue());
        assertTrue(or(TRUE, FALSE).getValue());
        assertTrue(or(FALSE, TRUE).getValue());
        assertFalse(or(FALSE, FALSE).getValue());
    }

    @Test
    public void notTest() {
        assertTrue(not(FALSE).getValue());
        assertFalse(not(TRUE).getValue());
    }

    @Test
    public void equalsTest() {
        assertTrue(equalsTo(new SString("hello"), new SString("hello")).getValue());
        assertFalse(equalsTo(new SString("hello"), new SString("world")).getValue());
        assertTrue(equalsTo(new SNumber(100), new SNumber(50 * 2)).getValue());
    }
}
