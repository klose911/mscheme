package org.klose.scheme.service;

import org.klose.scheme.constant.SConstant;
import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SFrame;
import org.klose.scheme.type.SObject;

import java.util.List;

class SequenceService {
    static SObject evalSequence(SExpression exp, SFrame env)
            throws IllegalExpressionException, WrongArgumentNumberException {
        if (exp == null)
            throw new IllegalArgumentException("expression of evalSequence can not be null");
        if (env == null)
            throw new IllegalArgumentException("environment of evalSequence can not be null");

        if (exp.getChildren().size() < 2)
            throw new WrongArgumentNumberException(2, exp.getChildren().size(), "evalSequence");

        SObject result = SConstant.NIL;
        List<SExpression> children = exp.getChildren();
        for (int i = 1; i < children.size(); i++)
            result = EvalService.eval(children.get(i), env);

        return result;
    }
}
