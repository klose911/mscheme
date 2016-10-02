package org.klose.scheme.primitive;

import org.klose.scheme.SContext;
import org.klose.scheme.SExpression;
import org.klose.scheme.SNumber;

import java.util.function.BiFunction;


public class BiFunctionTest {
    public static void main(String [] args) {
        BiFunction<SExpression[], SContext, SNumber> addFunc =
        (SExpression[] _args, SContext context) -> {
            Long result = 0L;
            for(SExpression a: _args) {
                result += ((SNumber) a.evalute(context)).getValue();
            }
            return new SNumber(result);
        };


        SExpression[] exprs = {
                new SExpression("100", null),
                new SExpression("200", null)
        };

        SNumber result = addFunc.apply(exprs, new SContext(null));
        System.out.println(result.getValue());
    }
}
