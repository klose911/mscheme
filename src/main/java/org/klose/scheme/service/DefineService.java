package org.klose.scheme.service;

import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SFrame;
import org.klose.scheme.model.SProcedure;
import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SString;

import java.util.ArrayList;
import java.util.List;

class DefineService {
    static SString evalDefine(SExpression exp, SFrame env)
            throws IllegalExpressionException, WrongArgumentNumberException {
        if (exp == null)
            throw new IllegalArgumentException("expression of evalDefine can not be null");
        if (env == null)
            throw new IllegalArgumentException("environment of evalDefine can not be null");

        if (exp.getChildren().size() != 3)
            throw new WrongArgumentNumberException(3, exp.getChildren().size(), "evalDefine");

        env.define(getDefinitionVariable(exp), getDefinitionValue(exp, env));
        return new SString("ok");
    }


    private static String getDefinitionVariable(SExpression exp)
            throws IllegalExpressionException {
        assert (exp != null);
        assert (exp.getChildren() != null);
        assert (exp.getChildren().size() == 3);

        SExpression variableExpression = exp.getChildren().get(1);
        if (variableExpression.isSelfEval())
            return variableExpression.getValue();
        else {
            List<SExpression> variableList = variableExpression.getChildren();
            if (variableList.isEmpty())
                throw new IllegalExpressionException("variable list of define can not be empty");

            return variableList.get(0).getValue();
        }
    }

    private static SObject getDefinitionValue(SExpression exp, SFrame env)
            throws IllegalExpressionException, WrongArgumentNumberException {
        assert (exp != null);
        assert (exp.getChildren() != null);
        assert (exp.getChildren().size() == 3);
        assert (env != null);

        SExpression variableExpression = exp.getChildren().get(1);
        SExpression valueExpression = exp.getChildren().get(2);

        if (variableExpression.isSelfEval())
            return EvalService.eval(valueExpression, env);
        else {
            List<SExpression> variableList = variableExpression.getChildren();
            if (variableList.isEmpty())
                throw new IllegalExpressionException("variable list of define can not be empty");

            return new SProcedure(valueExpression,
                    getProcedureParameters(variableList), env);
        }
    }

    private static List<String> getProcedureParameters(List<SExpression> variableList) {
        assert (variableList != null);
        assert (!variableList.isEmpty());

        List<String> params = new ArrayList<>();
        for (int i = 1; i < variableList.size(); i++)
            params.add(variableList.get(i).getValue());

        return params;
    }
}
