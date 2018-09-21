package org.klose.scheme.func;

import org.junit.Test;
import org.klose.scheme.type.*;

import static junit.framework.Assert.*;
import static org.klose.scheme.constant.SConstant.NIL;
import static org.klose.scheme.primitive.ListFunc.isNull;
import static org.klose.scheme.primitive.ListFunc.list;
import static org.klose.scheme.primitive.PairFunc.*;

public class ListFuncTest {

    @Test
    public void nullList() {
        SList list = list();
        assertEquals(NIL, list);
        assertTrue(isNull(list).getValue());
        assertNull(car(list));
        assertNull(cdr(list));
    }

    @Test
    public void notNullList() {
        SList list = list(NIL);
        assertFalse(isNull(list).getValue());
    }

    @Test
    public void singleElementList() {
        SList list = list(new SNumber(100));
        assertEquals(100, car(list).getValue());
        assertEquals(NIL, cdr(list));
    }

    @Test
    public void multiElementsList() {
        SList list = list(new SNumber(10), new SNumber(20d),
                new SBoolean(true), new SString("hello"));
        assertEquals(10, car(list).getValue());
        assertEquals(20d, car(cdr(list)).getValue());
        assertTrue((Boolean) car(cdr(cdr(list))).getValue());
        assertEquals("hello", car(cdr(cdr(cdr(list)))).getValue());
    }

    @Test
    public void hierarchyList() {
        SList list = list(new SNumber(100),
                list(new SBoolean(false), new SString("world")),
                cons(new SNumber(21.f), new SNumber(-200L)));
        assertEquals(100, car(list).getValue());
        assertTrue(car(cdr(list)) instanceof SList);
        assertTrue(car(car(cdr(list))) instanceof SBoolean);
        assertFalse((Boolean) car(car(cdr(list))).getValue());
        assertTrue(car(cdr(car(cdr(list)))) instanceof SString);
        assertEquals("world", (car(cdr(car(cdr(list)))).getValue()));
        assertTrue(car(cdr(cdr(list))) instanceof SPair);
        assertEquals(21.f, car(car(cdr(cdr(list)))).getValue());
        assertEquals(-200L, cdr(car(cdr(cdr(list)))).getValue());
    }

}
