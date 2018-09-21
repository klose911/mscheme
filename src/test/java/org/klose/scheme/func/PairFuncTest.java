package org.klose.scheme.func;

import org.junit.Test;
import org.klose.scheme.primitive.ListFunc;
import org.klose.scheme.primitive.PairFunc;
import org.klose.scheme.type.*;

import static junit.framework.Assert.*;
import static org.klose.scheme.primitive.PairFunc.car;
import static org.klose.scheme.primitive.PairFunc.cdr;

public class PairFuncTest {

    @Test
    public void cons() {
        SPair pair = PairFunc.cons(new SNumber(100d), new SString("hello world"));
        assertEquals(100d, ((SNumber) car(pair)).getValue());
        assertEquals("hello world", cdr(pair).getValue());
    }

    @Test
    public void consList() {
        SPair pair = PairFunc.cons(new SNumber(100),
                ListFunc.list(new SBoolean(false), new SString("hello")));
        assertTrue(pair instanceof SList);
        assertEquals(100, car(pair).getValue());
        assertFalse(((SBoolean) car(cdr(pair))).getValue());
        assertEquals("hello", car(cdr(cdr(pair))).getValue());
    }
}
