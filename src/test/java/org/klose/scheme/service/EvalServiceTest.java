package org.klose.scheme.service;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.model.SEnvironment;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SString;

public class EvalServiceTest {

    @Test
    public void intValue() {
        final Integer i = 21;
        SExpression e = new SExpression(i.toString(), null);
        SObject o = EvalService.eval(e, new SEnvironment());
        Assert.assertTrue(o instanceof SNumber);
        Assert.assertEquals(i, ((SNumber) o).getValue());
    }

    @Test
    public void floatValue() {
        final Float f = 20.1f;
        SExpression e = new SExpression(f.toString(), null);
        SObject o = EvalService.eval(e, new SEnvironment());
        Assert.assertTrue(o instanceof SNumber);
        Assert.assertEquals(f, ((SNumber) o).getValue());
    }

    @Test
    public void longValue() {
        final Long l = 100L;
        SExpression e = new SExpression("100L", null);
        SObject o = EvalService.eval(e, new SEnvironment());
        Assert.assertTrue(o instanceof SNumber);
        Assert.assertEquals(l, ((SNumber) o).getValue());
    }

    @Test
    public void doubleValue() {
        final Double v = 20.1d;
        SExpression e = new SExpression("20.1d", null);
        SObject o = EvalService.eval(e, new SEnvironment());
        Assert.assertTrue(o instanceof SNumber);
        Assert.assertEquals(v, ((SNumber) o).getValue());
    }

    @Test
    public void stringValue() {
        String s = "hello 'world'\n";
        SExpression e = new SExpression("\"" + s + "\"", null);
        SObject o = EvalService.eval(e, new SEnvironment());
        Assert.assertTrue(o instanceof SString);
        Assert.assertEquals(s, o.getStr());
    }

    @Test
    public void emptyStringValue() {
        String s = "";
        SExpression e = new SExpression("\"" + s + "\"", null);
        SObject o = EvalService.eval(e, new SEnvironment());
        Assert.assertTrue(o instanceof SString);
        Assert.assertEquals(s, o.getStr());
    }


}
