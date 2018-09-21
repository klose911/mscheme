package org.klose.scheme.func;

import org.junit.Test;
import org.klose.scheme.builtin.ListFunc;
import org.klose.scheme.builtin.PairFunc;
import org.klose.scheme.type.*;

import static junit.framework.Assert.*;
import static org.klose.scheme.builtin.PairFunc.car;
import static org.klose.scheme.builtin.PairFunc.cdr;

public class PairFuncTest {

    @Test
    public void cons() {
        SPair pair = PairFunc.cons(new SNumber(100d), new SString("hello world"));
        assertEquals(100d, ((SNumber) car(pair)).getValue());
        assertEquals("hello world", cdr(pair).getStr());
    }

    @Test
    public void consList() {
        SPair pair = PairFunc.cons(new SNumber(100),
                ListFunc.list(new SBoolean(false), new SString("hello")));
        assertTrue(pair instanceof SList);
        assertEquals(100, ((SNumber) car(pair)).getValue());
        assertFalse(((SBoolean) car(cdr(pair))).getValue());
        assertEquals("hello", car(cdr(cdr(pair))).getStr());
    }
}
