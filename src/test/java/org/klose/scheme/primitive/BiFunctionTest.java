package org.klose.scheme.primitive;

import org.klose.scheme.model.*;
import org.klose.scheme.service.EvalService;

import java.math.BigDecimal;
import java.util.function.BiFunction;


public class BiFunctionTest {
    public static void main(String[] args) {
        BiFunction<SExpression[], SContext, SObject> addFunc =
                (SExpression[] _args, SContext context) -> {
                    BigDecimal result = BigDecimal.ZERO;
                    BigDecimal operand;
                    SObject o;
                    boolean isFloat = false;
                    for (SExpression a : _args) {
                        o = EvalService.eval(a, context);
                        if (o instanceof SLong) {
                            operand = BigDecimal.valueOf(((SLong) o).getValue());
                        } else if(o instanceof  SFloat) {
                            isFloat = true;
                            operand = BigDecimal.valueOf(((SFloat) o).getValue());
                        }
                        else
                            throw new RuntimeException("illegal operands");
                        result = result.add(operand);
                    }
                    if(isFloat)
                        return new SFloat(result.floatValue());
                    else
                        return new SLong(result.longValue());
                };


        SExpression[] exprs = {
                new SExpression("100", null),
                new SExpression("-200", null),
                new SExpression("10001.223", null)
        };

        SObject result = addFunc.apply(exprs, new SContext(null));
        System.out.println(result);
    }
}
