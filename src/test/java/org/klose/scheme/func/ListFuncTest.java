package org.klose.scheme.func;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.builtin.ListFunc;
import org.klose.scheme.builtin.PairFunc;
import org.klose.scheme.constant.SConstant;
import org.klose.scheme.type.*;

public class ListFuncTest {

    @Test
    public void nullList() {
        SList l = ListFunc.list();
        Assert.assertEquals(SConstant.NIL, l);
        Assert.assertNull(l.getCar());
        Assert.assertNull(l.getCdr());
    }

    @Test
    public void singleElementList() {
        SList l = ListFunc.list(new SNumber(100));
        Assert.assertEquals(100, ((SNumber) l.getCar()).getValue());
        Assert.assertEquals(SConstant.NIL, l.getCdr());
    }

    @Test
    public void multipleElementlist() {
        SList l = ListFunc.list(new SNumber(10), new SNumber(20d),
                new SBoolean(true), new SString("hello"));
        Assert.assertEquals(10, ((SNumber) l.getCar()).getValue());
        Assert.assertEquals(20d, ((SNumber) l.getCdr().getCar()).getValue());
        Assert.assertTrue(((SBoolean) l.getCdr().getCdr().getCar()).getValue());
        Assert.assertEquals("hello", l.getCdr().getCdr().getCdr().getCar().getStr());
        Assert.assertEquals(SConstant.NIL, l.getCdr().getCdr().getCdr().getCdr());
    }

    @Test
    public void hierarchyList() {
        SList l = ListFunc.list(new SNumber(100),
                ListFunc.list(new SBoolean(false), new SString("world")),
                PairFunc.cons(new SNumber(21.f), new SNumber(-200L)));
        Assert.assertEquals(100, ((SNumber) l.getCar()).getValue());
        Assert.assertTrue(l.getCdr().getCar() instanceof SList);
        Assert.assertTrue(((SList) l.getCdr().getCar()).getCar() instanceof SBoolean);
        Assert.assertFalse(((SBoolean) ((SList) l.getCdr().getCar()).getCar()).getValue());
        Assert.assertTrue(((SList) l.getCdr().getCar()).getCdr().getCar() instanceof SString);
        Assert.assertEquals("world", ((SList) l.getCdr().getCar()).getCdr().getCar().getStr());
        Assert.assertTrue(l.getCdr().getCdr().getCar() instanceof SPair);
        Assert.assertEquals(21.f, ((SNumber) ((SPair) l.getCdr().getCdr().getCar()).getCar()).getValue());
        Assert.assertEquals(-200L, ((SNumber) ((SPair) l.getCdr().getCdr().getCar()).getCdr()).getValue());
    }
}
