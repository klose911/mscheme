package org.klose.scheme.service;

import org.klose.scheme.model.*;
import org.klose.scheme.utils.Assert;
import org.klose.scheme.utils.Utils;


public class EvalService {

    public static SObject eval(SExpression sExpr, SContext context) {
        Assert.isNotNull(sExpr);
        String str = sExpr.getStr();
        if (Utils.isLong(str))
            return new SLong(Long.valueOf(str));
        else if (Utils.isFloat(str))
            return new SFloat(Float.valueOf(str));
        else if (str.equals("true"))
            return new SBoolean(Boolean.TRUE);
        else if (str.equals("false"))
            return new SBoolean(Boolean.FALSE);
            //@TODO symbol
            //@TODO quote
        else if (sExpr.getChildren().get(0).getStr().equals("quote")) {
            return null;
        } //@TODO define
        else if (sExpr.getChildren().get(0).getStr().equals("define")) {
            return null;
        } //@TODO set!
        else if (sExpr.getChildren().get(0).getStr().equals("set!")) {
            return null;
        } //@TODO lambda
        else if (sExpr.getChildren().get(0).getStr().equals("lambda")) {
            return null;
        } //@TODO if
        else if (sExpr.getChildren().get(0).getStr().equals("if")) {
            return null;
        } //@TODO apply procedure
        else {
            return null;
        }
    }
}
