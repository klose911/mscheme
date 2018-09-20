package org.klose.scheme.service;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.model.SEnvironment;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SProcedure;
import org.klose.scheme.type.SFunc;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.utils.SParser;

import java.util.ArrayList;
import java.util.List;

public class ApplyServiceTest {

    @Test
    public void primitiveApply() {
        SFunc func = new SFunc("org.klose.scheme.builtin.AddFunc.add");
        SObject[] args = new SNumber[]{new SNumber(0.1d), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f)};
        SObject result = ApplyService.apply(func, args);
        Assert.assertTrue(result instanceof SNumber);
        Assert.assertEquals(111.1d, ((SNumber) result).getValue());
    }

    @Test
    public void compositeApply() {
        SEnvironment environment = new SEnvironment();
        environment.define("+", new SFunc("org.klose.scheme.builtin.AddFunc.add"));
        List<String> parameters = new ArrayList<>();
        parameters.add("x");
        parameters.add("y");
        SExpression exp = SParser.parse("( + x y)");
        SProcedure procedure = new SProcedure(exp, parameters, environment);
        SObject result = ApplyService.apply(procedure, new SNumber[]{new SNumber(100), new SNumber(200)});
        Assert.assertTrue(result instanceof SNumber);
        Assert.assertEquals(300, ((SNumber) result).getValue());
    }

    @Test
    public void anonymousApply() {
        SEnvironment env = new SEnvironment();
        env.define("+", new SFunc("org.klose.scheme.builtin.AddFunc.add"));
        env.define("*", new SFunc("org.klose.scheme.builtin.MultipleFunc.multiple"));
        SExpression exp = SParser.parse("(lambda (x y) (* 2 (+ x y) x y)");
        SProcedure procedure = (SProcedure) EvalService.eval(exp, env);
        SObject result = ApplyService.apply(procedure, new SNumber[]{new SNumber(100), new SNumber(200)});
        Assert.assertTrue(result instanceof SNumber);
        Assert.assertEquals(12000000, ((SNumber) result).getValue());
    }
}
