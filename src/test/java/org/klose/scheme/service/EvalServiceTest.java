package org.klose.scheme.service;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.model.SEnvironment;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SProcedure;
import org.klose.scheme.type.*;
import org.klose.scheme.utils.SParser;

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

    @Test
    public void lookUpVar() {
        SEnvironment env = new SEnvironment();
        env.define("x", new SNumber(100));
        SExpression exp = new SExpression("x", null);
        SObject o = EvalService.eval(exp, env);
        Assert.assertEquals(100, ((SNumber) o).getValue());
    }

    @Test
    public void evalTrue() {
        SEnvironment env = new SEnvironment();
        env.define("true", new SBoolean(true));
        SObject o = EvalService.eval(new SExpression("true", null), env);
        Assert.assertTrue(o instanceof SBoolean);
        Assert.assertTrue(((SBoolean) o).getValue());
    }

    @Test
    public void evalFalse() {
        SEnvironment env = new SEnvironment();
        env.define("false", new SBoolean(false));
        SObject o = EvalService.eval(new SExpression("false", null), env);
        Assert.assertTrue(o instanceof SBoolean);
        Assert.assertFalse(((SBoolean) o).getValue());
    }

    @Test
    public void evalQuote() {
        SEnvironment env = new SEnvironment();
        SExpression exp = SParser.parse("(quote (a b))");
        SObject o = EvalService.eval(exp, env);
        Assert.assertTrue(o instanceof SExpression);
        Assert.assertEquals("(a b)", o.toString());
    }

    @Test
    public void evalDefine() {
        SEnvironment env = new SEnvironment();
        SExpression exp = SParser.parse("(define a 100)");
        SObject o = EvalService.eval(exp, env);
        Assert.assertTrue(o instanceof SString);
        Assert.assertEquals("ok", o.getStr());
        Assert.assertEquals(100, ((SNumber) env.lookup("a")).getValue());
    }

    @Test
    public void evalAssign() {
        SEnvironment env = new SEnvironment();
        SExpression exp = SParser.parse("(define a 100)");
        EvalService.eval(exp, env);
        SExpression exp1 = SParser.parse("(set! a 200)");
        SObject o = EvalService.eval(exp1, env);
        Assert.assertTrue(o instanceof SString);
        Assert.assertEquals("ok", o.getStr());
        Assert.assertEquals(200, ((SNumber) env.lookup("a")).getValue());
    }

    @Test
    public void evalIfWithConsequent() {
        SEnvironment env = new SEnvironment();
        env.define("false", new SBoolean(false));
        env.define("true", new SBoolean(true));
        env.define(">", new SFunc("org.klose.scheme.builtin.CompareFunc.greater"));
        env.define("+", new SFunc("org.klose.scheme.builtin.AddFunc.add"));
        env.define("*", new SFunc("org.klose.scheme.builtin.MultipleFunc.multiple"));
        SObject result = EvalService.eval(SParser.parse("(if (> -5.123d -200L) (+ 1 2) (* 2 3))"), env);
        Assert.assertTrue(result instanceof SNumber);
        Assert.assertEquals(3, ((SNumber) result).getValue());
    }

    @Test
    public void evalIfWithAlternative() {
        SEnvironment env = new SEnvironment();
        env.define("false", new SBoolean(false));
        env.define("true", new SBoolean(true));
        env.define(">", new SFunc("org.klose.scheme.builtin.CompareFunc.greater"));
        env.define("+", new SFunc("org.klose.scheme.builtin.AddFunc.add"));
        env.define("*", new SFunc("org.klose.scheme.builtin.MultipleFunc.multiple"));
        SObject result = EvalService.eval(SParser.parse("(if (> 3 5.0f) (+ 1 2) (* 2 3))"), env);
        Assert.assertTrue(result instanceof SNumber);
        Assert.assertEquals(6, ((SNumber) result).getValue());
    }

    @Test
    public void evalIfWithoutAlternative() {
        SEnvironment env = new SEnvironment();
        env.define("false", new SBoolean(false));
        env.define("true", new SBoolean(true));
        env.define(">", new SFunc("org.klose.scheme.builtin.CompareFunc.greater"));
        env.define("+", new SFunc("org.klose.scheme.builtin.AddFunc.add"));
        env.define("*", new SFunc("org.klose.scheme.builtin.MultipleFunc.multiple"));
        SObject result = EvalService.eval(SParser.parse("(if (> 3 5.0f) (+ 1 2))"), env);
        Assert.assertTrue(result instanceof SBoolean);
        Assert.assertFalse(((SBoolean) result).getValue());
    }

    @Test
    public void evalLambda() {
        SEnvironment env = new SEnvironment();
        SExpression exp = SParser.parse("(lambda (x y) (* x y)");
        SProcedure procedure = (SProcedure) EvalService.eval(exp, env);
        Assert.assertNotNull(procedure);
        Assert.assertEquals(2, procedure.getParameters().size());
        Assert.assertEquals("x", procedure.getParameters().get(0));
        Assert.assertEquals("y", procedure.getParameters().get(1));
        Assert.assertEquals(SParser.parse("(* x y)").toString(), procedure.getBody().toString());
        Assert.assertEquals(env, procedure.getContext());
    }
}
