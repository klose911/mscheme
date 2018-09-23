package org.klose.scheme.service;

import org.junit.Test;
import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.model.SFrame;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SProcedure;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SPrimitive;
import org.klose.scheme.utils.InitEnv;
import org.klose.scheme.utils.SParser;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.klose.scheme.service.ApplyService.apply;
import static org.klose.scheme.service.EvalService.eval;

public class ApplyServiceTest {

    @Test
    public void primitiveApply() throws IllegalExpressionException, WrongArgumentNumberException {
        SPrimitive func = new SPrimitive("org.klose.scheme.primitive.AddFunc.add");
        SObject[] args = new SNumber[]{new SNumber(0.1d), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f)};
        SObject result = apply(func, args);
        assertTrue(result instanceof SNumber);
        assertEquals(111.1d, result.getValue());
    }

    @Test
    public void compositeApply() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame environment = InitEnv.init();
        List<String> parameters = new ArrayList<>();
        parameters.add("x");
        parameters.add("y");
        SExpression exp = SParser.parse("( + x y)");
        SProcedure procedure = new SProcedure(exp, parameters, environment);
        SObject result = apply(procedure, new SNumber[]{new SNumber(100), new SNumber(200)});
        assertTrue(result instanceof SNumber);
        assertEquals(300, result.getValue());
    }

    @Test
    public void anonymousApply() throws IllegalExpressionException, WrongArgumentNumberException {
        SFrame env = InitEnv.init();
        SExpression exp = SParser.parse("(lambda (x y) (* 2 (+ x y) x y))");
        SProcedure procedure = (SProcedure) eval(exp, env);
        SObject result = apply(procedure, new SNumber[]{new SNumber(100), new SNumber(200)});
        assertTrue(result instanceof SNumber);
        assertEquals(12000000, result.getValue());
    }
}
