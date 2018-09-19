package org.klose.scheme.service;

import org.klose.scheme.model.SEnvironment;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.type.SObject;


public class EvalService {

    public static SObject eval(SExpression exp, SEnvironment env) {
        assert (exp != null);
        assert (env != null);

        return new SObject(exp.getStr());
    }

}
