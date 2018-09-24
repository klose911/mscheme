package org.klose.scheme.func;

import org.junit.Test;
import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.exception.WrongArgumentTypeException;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SFrame;
import org.klose.scheme.model.SProcedure;
import org.klose.scheme.primitive.ApplyFunc;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SPrimitive;
import org.klose.scheme.utils.InitEnv;
import org.klose.scheme.utils.SParser;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.klose.scheme.service.EvalService.eval;

public class ApplyFuncTest {

    @Test
    public void primitiveApply()
            throws IllegalExpressionException, WrongArgumentTypeException, WrongArgumentNumberException {
        SPrimitive func = new SPrimitive("org.klose.scheme.primitive.AddFunc.add");
        SObject result = ApplyFunc.apply(func, new SNumber(0.1d), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f));
        assertTrue(result instanceof SNumber);
        assertEquals(111.1d, result.getValue());
    }

    @Test
    public void anonymousApply()
            throws IllegalExpressionException, WrongArgumentNumberException, WrongArgumentTypeException {
        SFrame env = InitEnv.init();
        SExpression exp = SParser.parse("(lambda (x y) (* 2 (+ x y) x y))");
        SProcedure procedure = (SProcedure) eval(exp, env);
        SObject result = ApplyFunc.apply(procedure, new SNumber(100), new SNumber(200));
        assertTrue(result instanceof SNumber);
        assertEquals(12000000, result.getValue());
    }
}
