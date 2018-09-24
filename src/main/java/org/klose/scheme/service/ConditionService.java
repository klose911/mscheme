package org.klose.scheme.service;

import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SFrame;
import org.klose.scheme.type.SBoolean;
import org.klose.scheme.type.SObject;

class ConditionService {
    static SObject evalIf(SExpression exp, SFrame env)
            throws IllegalExpressionException, WrongArgumentNumberException {
        if (exp == null)
            throw new IllegalArgumentException("expression of evalIf can not be null");
        if (env == null)
            throw new IllegalArgumentException("environment of evalIf can not be null");

        if (exp.getChildren().size() < 3)
            throw new WrongArgumentNumberException(3, exp.getChildren().size(), "evalIf");

        // evaluate the predicate expression
        SExpression predicate = exp.getChildren().get(1);
        SBoolean check = (SBoolean) EvalService.eval(predicate, env);
        if (check.getValue()) {
            //evaluate the consequent if the predicate is true
            SExpression consequent = exp.getChildren().get(2);
            return EvalService.eval(consequent, env);
        } else {
            //if alternative available, evaluate the alternative
            if (exp.getChildren().size() == 4) {
                SExpression alternative = exp.getChildren().get(3);
                return EvalService.eval(alternative, env);
            } else
                //if alternative misses, directly return false
                return new SBoolean(false);
        }
    }
}
