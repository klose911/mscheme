package org.klose.scheme.service;

import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SFrame;
import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SString;

class AssignService {
    static SString evalAssign(SExpression exp, SFrame env)
            throws IllegalExpressionException, WrongArgumentNumberException {
        if (exp == null)
            throw new IllegalArgumentException("expression of evalAssign can not be null");
        if (env == null)
            throw new IllegalArgumentException("environment of evalAssign can not be null");

        if (exp.getChildren().size() != 3)
            throw new WrongArgumentNumberException(3, exp.getChildren().size(), "evalAssign");

        String var = exp.getChildren().get(1).getValue();
        SObject value = EvalService.eval(exp.getChildren().get(2), env);
        //modify the binding of a variable under the environment
        env.assign(var, value);
        return new SString("ok");
    }
}
