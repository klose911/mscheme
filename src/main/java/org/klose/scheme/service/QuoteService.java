package org.klose.scheme.service;

import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.type.SObject;

class QuoteService {
    static SObject evalQuote(SExpression exp) throws WrongArgumentNumberException {
        if (exp == null)
            throw new IllegalArgumentException("expression of evalQuote can not be null");

        if (exp.getChildren().size() != 2)
            throw new WrongArgumentNumberException(2, exp.getChildren().size(), "quote");

        //returns the expression that was quoted
        return exp.getChildren().get(1);
    }
}
