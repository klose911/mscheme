package org.klose.scheme.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.klose.scheme.model.SEnvironment;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SProcedure;
import org.klose.scheme.type.SBoolean;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SString;
import org.klose.scheme.utils.EvalUtils;

import java.util.ArrayList;
import java.util.List;


public class EvalService {

    public static SObject eval(SExpression exp, SEnvironment env) {
        if (exp == null)
            throw new IllegalArgumentException("expression can not be null");
        if (env == null)
            throw new IllegalArgumentException("environment can not be null");

        if (exp.hasNoneChild()) {
            String str = exp.getStr();
            if (EvalUtils.isNumber(str))
                return new SNumber(NumberUtils.createNumber(str));
            else if (EvalUtils.isString(str))
                return new SString(StringUtils.substring(str, 1,
                        str.length() - 1));
            else
                return env.lookup(str);
        }

        SExpression s0 = exp.getChildren().get(0);
        switch (s0.getStr()) {
            case "quoted":
                return exp.getChildren().get(1);
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

        SProcedure procedure = (SProcedure) EvalService.eval(s0, env);
        if (procedure != null)
            return ApplyService.apply(procedure, listOfOperands(exp, env));
        else
            throw new RuntimeException("\"Unknown expression type -- EVAL " + exp);
    }

    private static SObject[] listOfOperands(SExpression exp, SEnvironment env) {
        if (!exp.getChildren().isEmpty()) {
            int paramSize = exp.getChildren().size() - 1;
            SObject[] operands = new SObject[paramSize];
            for (int i = 0; i < paramSize; i++)
                operands[i] = EvalService.eval(exp.getChildren().get(i + 1), env);

            return operands;
        } else
            throw new IllegalArgumentException("expression is not procedure");
    }

    private static SObject evalIf(SExpression exp, SEnvironment env) {
        SExpression predicate = exp.getChildren().get(1);
        SBoolean check = (SBoolean) EvalService.eval(predicate, env);
        if (check.getValue()) {
            SExpression consequent = exp.getChildren().get(2);
            return EvalService.eval(consequent, env);
        } else {
            if (exp.getChildren().size() == 4) {
                SExpression alternative = exp.getChildren().get(4);
                return EvalService.eval(alternative, env);
            } else
                return new SBoolean(false);
        }
    }

    private static SString evalDefine(SExpression exp, SEnvironment env) {
        String var = exp.getChildren().get(1).getStr();
        SObject value = EvalService.eval(exp.getChildren().get(2), env);
        env.define(var, value);
        return new SString("ok");
    }

    private static SString evalAssign(SExpression exp, SEnvironment env) {
        String var = exp.getChildren().get(1).getStr();
        SObject value = EvalService.eval(exp.getChildren().get(2), env);
        env.assign(var, value);
        return new SString("ok");
    }

    private static SProcedure evalLambda(SExpression exp, SEnvironment env) {
        SExpression body = exp.getChildren().get(2);
        SExpression parametersExp = exp.getChildren().get(1);
        List<String> parameters = new ArrayList<>();
        for (SExpression p : parametersExp.getChildren()) {
            parameters.add(p.getStr());
        }
        return new SProcedure(body, parameters, env);
    }
}
