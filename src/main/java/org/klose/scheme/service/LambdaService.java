package org.klose.scheme.service;

import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SFrame;
import org.klose.scheme.model.SProcedure;

import java.util.ArrayList;
import java.util.List;

class LambdaService {
    static SProcedure evalLambda(SExpression exp, SFrame env) throws WrongArgumentNumberException {
        if (exp == null)
            throw new IllegalArgumentException("expression of evalLambda can not be null");
        if (env == null)
            throw new IllegalArgumentException("environment of evalLambda can not be null");

        if (exp.getChildren().size() != 3)
            throw new WrongArgumentNumberException(3, exp.getChildren().size(), "evalLambda");

        // transformed into an applicable procedure by packaging together the parameters
        // and body specified by the lambda expression with the environment of the evaluation
        SExpression body = exp.getChildren().get(2);
        SExpression parametersExp = exp.getChildren().get(1);
        List<String> parameters = new ArrayList<>();
        for (SExpression p : parametersExp.getChildren())
            parameters.add(p.getValue());


        return new SProcedure(body, parameters, env);
    }
}
