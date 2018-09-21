package org.klose.scheme.func;

import org.junit.Test;
import org.klose.scheme.constant.SConstant;
import org.klose.scheme.type.*;

import static junit.framework.Assert.*;
import static org.klose.scheme.builtin.ListFunc.list;
import static org.klose.scheme.builtin.PairFunc.*;

public class ListFuncTest {

    @Test
    public void nullList() {
        SList list = list();
        assertEquals(SConstant.NIL, list);
        assertNull(car(list));
        assertNull(cdr(list));
    }

    @Test
    public void singleElementList() {
        SList list = list(new SNumber(100));
        assertEquals(100, ((SNumber) car(list)).getValue());
        assertEquals(SConstant.NIL, cdr(list));
    }

    @Test
    public void multiElementsList() {
        SList list = list(new SNumber(10), new SNumber(20d),
                new SBoolean(true), new SString("hello"));
        assertEquals(10, ((SNumber) car(list)).getValue());
        assertEquals(20d, ((SNumber) car(cdr(list))).getValue());
        assertTrue(((SBoolean) car(cdr(cdr(list)))).getValue());
        assertEquals("hello", car(cdr(cdr(cdr(list)))).getStr());
    }

    @Test
    public void hierarchyList() {
        SList list = list(new SNumber(100),
                list(new SBoolean(false), new SString("world")),
                cons(new SNumber(21.f), new SNumber(-200L)));
        assertEquals(100, ((SNumber) car(list)).getValue());
        assertTrue(car(cdr(list)) instanceof SList);
        assertTrue(car(car(cdr(list))) instanceof SBoolean);
        assertFalse(((SBoolean) car(car(cdr(list)))).getValue());
        assertTrue(car(cdr(car(cdr(list)))) instanceof SString);
        assertEquals("world", (car(cdr(car(cdr(list)))).getStr()));
        assertTrue(car(cdr(cdr(list))) instanceof SPair);
        assertEquals(21.f, ((SNumber) car(car(cdr(cdr(list))))).getValue());
        assertEquals(-200L, ((SNumber) cdr(car(cdr(cdr(list))))).getValue());
    }
}
