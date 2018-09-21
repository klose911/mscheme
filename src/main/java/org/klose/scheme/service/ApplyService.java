package org.klose.scheme.service;


import org.klose.scheme.model.SEnvironment;
import org.klose.scheme.model.SProcedure;
import org.klose.scheme.type.SBoolean;
import org.klose.scheme.type.SPrimitive;
import org.klose.scheme.type.SObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ApplyService {

    public static SObject apply(SProcedure procedure, SObject[] arguments) {
        if (procedure.isPrimitive())
            return applyPrimitive((SPrimitive) procedure, arguments);
        else
            return applyComposite(procedure, arguments);
    }

    private static SObject applyPrimitive(SPrimitive func, SObject[] arguments) {
        Class clazz;
        try {
            clazz = Class.forName(func.getClazz());
            Optional<Method> o = Arrays.stream(clazz.getMethods())
                    .filter(x -> x.getName().equals(func.getMethod()))
                    .findFirst();
            if (o.isPresent()) {
                try {
                    return (SObject) o.get().invoke(null, new Object[]{arguments});
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new SBoolean(false);
    }

    private static SObject applyComposite(SProcedure procedure, SObject[] arguments) {
        if (procedure.getParameters().size() > arguments.length)
            throw new IllegalArgumentException("less arguments");
        else if (procedure.getParameters().size() < arguments.length)
            throw new IllegalArgumentException("more arguments");

        Map<String, SObject> vars = new HashMap<>();
        for (int i = 0; i < arguments.length; i++)
            vars.put(procedure.getParameters().get(i), arguments[i]);

        SEnvironment extendedEnv = procedure.getContext().extend(vars);

        return EvalService.eval(procedure.getBody(), extendedEnv);
    }
}
