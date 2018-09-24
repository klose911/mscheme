package org.klose.scheme.service;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.constant.SConstant;
import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SFrame;
import org.klose.scheme.model.SProcedure;
import org.klose.scheme.type.*;
import org.klose.scheme.utils.SParser;

import java.util.HashMap;

import static junit.framework.Assert.*;
import static org.klose.scheme.service.EvalService.eval;

public class EvalServiceTest {

    @Test
    public void intValue() throws IllegalExpressionException, WrongArgumentNumberException {
        final Integer i = 21;
        SExpression e = new SExpression(i.toString(), null);
        SObject o = eval(e, new SFrame(new HashMap<>(), null));
        assertTrue(o instanceof SNumber);
        assertEquals(i, o.getValue());
    }

    @Test
    public void floatValue() throws IllegalExpressionException, WrongArgumentNumberException {
        final Float f = 20.1f;
        SExpression e = new SExpression(f.toString(), null);
        SObject o = eval(e, new SFrame(new HashMap<>(), null));
        assertTrue(o instanceof SNumber);
        assertEquals(f, o.getValue());
    }

    @Test
    public void longValue() throws IllegalExpressionException, WrongArgumentNumberException {
        final Long l = 100L;
        SExpression e = new SExpression("100L", null);
        SObject o = eval(e, new SFrame(new HashMap<>(), null));
        assertTrue(o instanceof SNumber);
        assertEquals(l, o.getValue());
    }

    @Test
    public void doubleValue() throws IllegalExpressionException, WrongArgumentNumberException {
        final Double v = 20.1d;
        SExpression e = new SExpression("20.1d", null);
        SObject o = eval(e, new SFrame(new HashMap<>(), null));
        assertTrue(o instanceof SNumber);
        assertEquals(v, o.getValue());
    }

    @Test
    public void stringValue() throws IllegalExpressionException, WrongArgumentNumberException {
        String s = "hello 'world'\n";
        SExpression e = new SExpression("\"" + s + "\"", null);
        SObject o = eval(e, new SFrame(new HashMap<>(), null));
        assertTrue(o instanceof SString);
        assertEquals(s, o.getValue());
    }

    @Test
    public void emptyStringValue() throws IllegalExpressionException, WrongArgumentNumberException {
        String s = "";
        SExpression e = new SExpression("\"" + s + "\"", null);
        SObject o = eval(e, new SFrame(new HashMap<>(), null));
        assertTrue(o instanceof SString);
        assertEquals(s, o.getValue());
    }

    @Test
    public void lookUpVar() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame env = new SFrame(new HashMap<>(), null);
        env.define("x", new SNumber(100));
        SExpression exp = new SExpression("x", null);
        SObject o = eval(exp, env);
        assertEquals(100, o.getValue());
    }

    @Test
    public void evalTrue() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame env = new SFrame(new HashMap<>(), null);
        env.define("true", new SBoolean(true));
        SObject o = eval(new SExpression("true", null), env);
        assertTrue(o instanceof SBoolean);
        assertTrue((Boolean) o.getValue());
    }

    @Test
    public void evalFalse() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame env = new SFrame(new HashMap<>(), null);
        env.define("false", new SBoolean(false));
        SObject o = eval(new SExpression("false", null), env);
        assertTrue(o instanceof SBoolean);
        assertFalse((Boolean) o.getValue());
    }

    @Test
    public void evalQuote() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame env = new SFrame(new HashMap<>(), null);
        SExpression exp = SParser.parse("(quote (a b))");
        SObject o = eval(exp, env);
        assertTrue(o instanceof SExpression);
        assertEquals("(a b)", o.getValue());
    }

    @Test
    public void evalDefine() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame env = new SFrame(new HashMap<>(), null);
        SExpression exp = SParser.parse("(define a 100)");
        SObject o = eval(exp, env);
        assertTrue(o instanceof SString);
        assertEquals("ok", o.getValue());
        assertEquals(100, (env.lookup("a")).getValue());
    }

    @Test
    public void evalDefineProcedure() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame env = new SFrame(new HashMap<>(), null);
        SExpression exp = SParser.parse("(define (add x y) (+ x y))");
        SObject o = eval(exp, env);
        assertTrue(o instanceof SString);
        assertEquals("ok", o.getValue());

        SProcedure procedure = (SProcedure) env.lookup("add");
        Assert.assertEquals("(+ x y)", procedure.getBody().toString());
        Assert.assertEquals("x", procedure.getParameters().get(0));
        Assert.assertEquals("y", procedure.getParameters().get(1));
    }

    @Test
    public void evalAssign() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame env = new SFrame(new HashMap<>(), null);
        SExpression exp = SParser.parse("(define a 100)");
        eval(exp, env);
        SExpression exp1 = SParser.parse("(set! a 200)");
        SObject o = eval(exp1, env);
        assertTrue(o instanceof SString);
        assertEquals("ok", o.getValue());
        assertEquals(200, (env.lookup("a")).getValue());
    }

    @Test
    public void evalIfWithConsequent() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame env = new SFrame(new HashMap<>(), null);
        env.define("false", new SBoolean(false));
        env.define("true", new SBoolean(true));
        env.define(">", new SPrimitive("org.klose.scheme.primitive.CompareFunc.greater"));
        env.define("+", new SPrimitive("org.klose.scheme.primitive.AddFunc.add"));
        env.define("*", new SPrimitive("org.klose.scheme.primitive.MultipleFunc.multiple"));
        SObject result = eval(SParser.parse("(if (> -5.123d -200L) (+ 1 2) (* 2 3))"), env);
        assertTrue(result instanceof SNumber);
        assertEquals(3, result.getValue());
    }

    @Test
    public void evalIfWithAlternative() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame env = new SFrame(new HashMap<>(), null);
        env.define("false", new SBoolean(false));
        env.define("true", new SBoolean(true));
        env.define(">", new SPrimitive("org.klose.scheme.primitive.CompareFunc.greater"));
        env.define("+", new SPrimitive("org.klose.scheme.primitive.AddFunc.add"));
        env.define("*", new SPrimitive("org.klose.scheme.primitive.MultipleFunc.multiple"));
        SObject result = eval(SParser.parse("(if (> 3 5.0f) (+ 1 2) (* 2 3))"), env);
        assertTrue(result instanceof SNumber);
        assertEquals(6, result.getValue());
    }

    @Test
    public void evalIfWithoutAlternative() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame env = new SFrame(new HashMap<>(), null);
        env.define("false", new SBoolean(false));
        env.define("true", new SBoolean(true));
        env.define(">", new SPrimitive("org.klose.scheme.primitive.CompareFunc.greater"));
        env.define("+", new SPrimitive("org.klose.scheme.primitive.AddFunc.add"));
        env.define("*", new SPrimitive("org.klose.scheme.primitive.MultipleFunc.multiple"));
        SObject result = eval(SParser.parse("(if (> 3 5.0f) (+ 1 2))"), env);
        assertTrue(result instanceof SBoolean);
        assertFalse((Boolean) result.getValue());
    }

    @Test
    public void evalLambda() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame env = new SFrame(new HashMap<>(), null);
        SExpression exp = SParser.parse("(lambda (x y) (* x y))");
        SProcedure procedure = (SProcedure) eval(exp, env);
        assertNotNull(procedure);
        assertEquals(2, procedure.getParameters().size());
        assertEquals("x", procedure.getParameters().get(0));
        assertEquals("y", procedure.getParameters().get(1));
        assertEquals(SParser.parse("(* x y)").toString(), procedure.getBody().toString());
        assertEquals(env, procedure.getEnvironment());
    }

    @Test
    public void evalSequence() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame env = new SFrame(new HashMap<>(), null);
        env.define("+", new SPrimitive("org.klose.scheme.primitive.AddFunc.add"));
        env.define("false", SConstant.FALSE);
        SExpression exp = SParser.parse("(begin false (+ 1 2))");
        SObject result = EvalService.eval(exp, env);
        Assert.assertEquals(3, result.getValue());
    }

    @Test
    public void evalSequenceWithAssign() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame env = new SFrame(new HashMap<>(), null);
        env.define("+", new SPrimitive("org.klose.scheme.primitive.AddFunc.add"));
        SExpression exp =SParser.parse("(begin (define a 1) (set! a 2) a)");
        SObject result = EvalService.eval(exp, env);
        Assert.assertEquals(2, result.getValue());
    }
}
