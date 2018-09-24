package org.klose.scheme.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SFrame;
import org.klose.scheme.model.SProcedure;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SString;
import org.klose.scheme.utils.EvalUtils;

/**
 * Eval takes as arguments an expression and an environment and return evaluation value.
 * <p>
 * eg. eval("(+ 1 2)" env) will return 3
 */
public class EvalService {

    public static SObject eval(SExpression exp, SFrame env)
            throws IllegalExpressionException, WrongArgumentNumberException {
        if (exp == null)
            throw new IllegalArgumentException("expression can not be null");
        if (env == null)
            throw new IllegalArgumentException("environment can not be null");

        if (exp.isSelfEval()) {
            String str = exp.getValue();
            //such as numbers, strings, eval returns the expression itself.
            if (EvalUtils.isNumber(str))
                return new SNumber(NumberUtils.createNumber(str));
            else if (EvalUtils.isString(str))
                return new SString(StringUtils.substring(str, 1,
                        str.length() - 1));
            else
                //look up variables in the environment to find their values
                return env.lookup(str);
        }

        SExpression operator = exp.getChildren().get(0);
        if (operator == null)
            throw new IllegalExpressionException("operator can not be null");

        //special form
        switch (operator.getValue()) {
            case "quote":  // ("quote" a) => a
                return QuoteService.evalQuote(exp);
            case "if": // (if (> 2 1) "foo" "bar")
                return ConditionService.evalIf(exp, env);
            case "define": // (define a 1)
                return DefineService.evalDefine(exp, env);
            case "set!": //  (set! a 1)
                return AssignService.evalAssign(exp, env);
            case "lambda": // (lambda (x y) (+ x y))
                return LambdaService.evalLambda(exp, env);
            case "begin": // (begin (+ 1 2) (* 3 4)) => 12
                return SequenceService.evalSequence(exp, env);
            default:
                break;
        }

        //Otherwise the operator should be a procedure, then recursively evaluate the operator part and the operands of the combination.
        //The resulting procedure and arguments are passed to apply, which handles the actual procedure application.
        SProcedure procedure = (SProcedure) EvalService.eval(operator, env);
        if (procedure != null)
            return ApplyService.apply(procedure, listOfOperands(exp, env));
        else
            throw new IllegalExpressionException("Unknown expression type -- EVAL" + exp);
    }

    private static SObject[] listOfOperands(SExpression exp, SFrame env)
            throws IllegalExpressionException, WrongArgumentNumberException {
        assert (exp != null);
        assert (env != null);

        //evaluates each operand and returns a list of the corresponding values
        if (exp.getChildren() == null || exp.getChildren().isEmpty())
            throw new IllegalExpressionException("expression is not procedure");

        int paramSize = exp.getChildren().size() - 1;
        SObject[] operands = new SObject[paramSize];
        for (int i = 0; i < paramSize; i++)
            operands[i] = EvalService.eval(exp.getChildren().get(i + 1), env);

        return operands;
    }
}
