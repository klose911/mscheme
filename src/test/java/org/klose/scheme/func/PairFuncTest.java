package org.klose.scheme.func;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.builtin.PairFunc;
import org.klose.scheme.constant.SConstant;
import org.klose.scheme.type.*;

public class PairFuncTest {

    @Test
    public void cons() {
        SPair p = PairFunc.cons(new SNumber(100d), new SString("hello world"));
        Assert.assertEquals(100d, ((SNumber) PairFunc.car(p)).getValue());
        Assert.assertEquals("hello world", PairFunc.cdr(p).getStr());
    }

    @Test
    public void consList() {
        SPair p1 = PairFunc.cons(new SNumber(100d), SConstant.NIL);
        SPair p2 = PairFunc.cons(new SNumber(20), SConstant.NIL);
        SPair p3 = PairFunc.cons(p1, p2);
        System.out.println(p3);
    }
}
