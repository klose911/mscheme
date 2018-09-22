package org.klose.scheme.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SFrame;
import org.klose.scheme.model.SProcedure;
import org.klose.scheme.type.SBoolean;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SString;
import org.klose.scheme.utils.EvalUtils;

import java.util.ArrayList;
import java.util.List;


public class EvalService {

    public static SObject eval(SExpression exp, SFrame env)
            throws IllegalExpressionException, WrongArgumentNumberException {
        if (exp == null)
            throw new IllegalArgumentException("expression can not be null");
        if (env == null)
            throw new IllegalArgumentException("environment can not be null");

        if (exp.hasNoneChild()) {
            String str = exp.getValue();
            if (EvalUtils.isNumber(str))
                return new SNumber(NumberUtils.createNumber(str));
            else if (EvalUtils.isString(str))
                return new SString(StringUtils.substring(str, 1,
                        str.length() - 1));
            else
                return env.lookup(str);
        }

        SExpression operator = exp.getChildren().get(0);
        if (operator == null)
            throw new IllegalExpressionException("operator can not be null");

        switch (operator.getValue()) {
            case "quote":
                return evalQuote(exp);
            case "if":
                return evalIf(exp, env);
            case "define":
                return evalDefine(exp, env);
            case "set!":
                return evalAssign(exp, env);
            case "lambda":
                return evalLambda(exp, env);
            default:
                break;
        }

        SProcedure procedure = (SProcedure) EvalService.eval(operator, env);
        if (procedure != null)
            return ApplyService.apply(procedure, listOfOperands(exp, env));
        else
            throw new IllegalExpressionException("\"Unknown expression type -- EVAL" + exp);
    }

    private static SObject[] listOfOperands(SExpression exp, SFrame env)
            throws IllegalExpressionException, WrongArgumentNumberException {
        assert (exp != null);
        assert (env != null);

        if (exp.getChildren() == null || exp.getChildren().isEmpty())
            throw new IllegalExpressionException("expression is not procedure");

        int paramSize = exp.getChildren().size() - 1;
        SObject[] operands = new SObject[paramSize];
        for (int i = 0; i < paramSize; i++)
            operands[i] = EvalService.eval(exp.getChildren().get(i + 1), env);

        return operands;
    }

    private static SObject evalQuote(SExpression exp) throws WrongArgumentNumberException {
        assert (exp != null);
        assert (!exp.getChildren().isEmpty());

        if (exp.getChildren().size() != 2)
            throw new WrongArgumentNumberException(2, exp.getChildren().size(), "quote");

        return exp.getChildren().get(1);

    }

    private static SObject evalIf(SExpression exp, SFrame env)
            throws IllegalExpressionException, WrongArgumentNumberException {
        assert (exp != null);
        assert (env != null);

        if (exp.getChildren().size() < 3)
            throw new WrongArgumentNumberException(3, exp.getChildren().size(), "evalIf");

        SExpression predicate = exp.getChildren().get(1);
        SBoolean check = (SBoolean) EvalService.eval(predicate, env);
        if (check.getValue()) {
            SExpression consequent = exp.getChildren().get(2);
            return EvalService.eval(consequent, env);
        } else {
            if (exp.getChildren().size() == 4) {
                SExpression alternative = exp.getChildren().get(3);
                return EvalService.eval(alternative, env);
            } else
                return new SBoolean(false);
        }
    }

    private static SString evalDefine(SExpression exp, SFrame env)
            throws IllegalExpressionException, WrongArgumentNumberException {
        String var = exp.getChildren().get(1).getValue();
        SObject value = EvalService.eval(exp.getChildren().get(2), env);
        env.define(var, value);
        return new SString("ok");
    }

    private static SString evalAssign(SExpression exp, SFrame env)
            throws IllegalExpressionException, WrongArgumentNumberException {
        assert (exp != null);
        assert (env != null);
        if (exp.getChildren().size() != 3)
            throw new WrongArgumentNumberException(3, exp.getChildren().size(), "evalAssign");

        String var = exp.getChildren().get(1).getValue();
        SObject value = EvalService.eval(exp.getChildren().get(2), env);
        env.assign(var, value);
        return new SString("ok");
    }

    private static SProcedure evalLambda(SExpression exp, SFrame env) throws WrongArgumentNumberException {
        assert (exp != null);
        assert (env != null);

        if (exp.getChildren().size() != 3)
            throw new WrongArgumentNumberException(3, exp.getChildren().size(), "evalLambda");

        SExpression body = exp.getChildren().get(2);
        SExpression parametersExp = exp.getChildren().get(1);
        List<String> parameters = new ArrayList<>();
        for (SExpression p : parametersExp.getChildren()) {
            parameters.add(p.getValue());
        }
        return new SProcedure(body, parameters, env);
    }
}
