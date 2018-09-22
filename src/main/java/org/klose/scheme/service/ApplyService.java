package org.klose.scheme.service;


import org.apache.commons.lang3.StringUtils;
import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.exception.WrongArgumentTypeException;
import org.klose.scheme.model.SFrame;
import org.klose.scheme.model.SProcedure;
import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SPrimitive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class ApplyService {

    static SObject apply(SProcedure procedure, SObject[] arguments)
            throws IllegalExpressionException, WrongArgumentNumberException {
        if (procedure == null)
            throw new IllegalArgumentException("procedure can not be null");
        if (arguments == null)
            throw new IllegalArgumentException("arguments can not be null");

        if (procedure.isPrimitive()) {
            return applyPrimitive((SPrimitive) procedure, arguments);
        } else
            return applyComposite(procedure, arguments);
    }

    private static SObject applyPrimitive(SPrimitive func, SObject[] arguments)
            throws IllegalExpressionException {
        assert (func != null);
        assert (arguments != null);

        if (StringUtils.isEmpty(func.getClazz()))
            throw new IllegalArgumentException("primitive class can not empty");
        if (StringUtils.isEmpty(func.getMethod()))
            throw new IllegalArgumentException("primitive method can not empty");

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
                    Throwable t = e.getCause();
                    if (t instanceof WrongArgumentNumberException
                            || t instanceof WrongArgumentTypeException)
                        throw new IllegalExpressionException(t.getMessage());
                    else
                        throw new IllegalExpressionException(e);
                }
            } else {
                throw new IllegalExpressionException("primitive operator can not be found");
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalExpressionException("Class: " + func.getClazz() + " not found");
        }
    }

    private static SObject applyComposite(SProcedure procedure, SObject[] arguments)
            throws WrongArgumentNumberException, IllegalExpressionException {
        assert (procedure != null);
        assert (arguments != null);

        if (procedure.getBody() == null)
            throw new IllegalExpressionException("procedure body can not be null");
        if (procedure.getParameters() == null)
            throw new IllegalExpressionException("procedure parameters can not be null");
        if (procedure.getEnvironment() == null)
            throw new IllegalExpressionException("procedure environment can not be null");
        if (procedure.getParameters().size() != arguments.length)
            throw new WrongArgumentNumberException(procedure.getParameters().size(),
                    arguments.length, "anoymous procedure");

        Map<String, SObject> vars = new HashMap<>();
        for (int i = 0; i < arguments.length; i++)
            vars.put(procedure.getParameters().get(i), arguments[i]);

        SFrame extendedEnv = procedure.getEnvironment().extend(vars);

        return EvalService.eval(procedure.getBody(), extendedEnv);
    }
}
