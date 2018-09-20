package org.klose.scheme.service;

import org.junit.Test;
import org.klose.scheme.model.SEnvironment;
import org.klose.scheme.model.SExpression;
import org.klose.scheme.model.SProcedure;
import org.klose.scheme.type.SFunc;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;
import org.klose.scheme.utils.SParser;

import java.util.ArrayList;
import java.util.List;

public class ApplyServiceTest {

    @Test
    public void primitive() {
        SFunc func = new SFunc("org.klose.scheme.builtin.AddFunc.add");
        SObject[] args = new SNumber[]{new SNumber(0.1d), new SNumber(10),
                new SNumber(100L), new SNumber(1.0f)};
        SObject result = ApplyService.apply(func, args);
        System.out.println(result);
    }

    @Test
    public void composite() {
        SEnvironment environment = new SEnvironment();
        environment.define("+", new SFunc("org.klose.scheme.builtin.AddFunc.add"));
        List<String> parameters = new ArrayList<>();
        parameters.add("x");
        parameters.add("y");
        SExpression exp = SParser.parse("( + x y)");
        SProcedure procedure = new SProcedure(exp, parameters, environment);
        SObject result = ApplyService.apply(procedure, new SNumber[]{new SNumber(100), new SNumber(200)});
        System.out.println(result);
    }
}
