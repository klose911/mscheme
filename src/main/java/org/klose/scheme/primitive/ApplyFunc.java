package org.klose.scheme.primitive;

import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.exception.WrongArgumentTypeException;
import org.klose.scheme.model.SProcedure;
import org.klose.scheme.service.ApplyService;
import org.klose.scheme.type.SObject;

public class ApplyFunc {

    public static SObject apply(SObject... args)
            throws IllegalExpressionException, WrongArgumentNumberException, WrongArgumentTypeException {
        if (args == null)
            throw new IllegalArgumentException("args can not be null");
        if (args.length < 1)
            throw new WrongArgumentNumberException(2, args.length, "apply");

        if (args[0] == null)
            throw new IllegalArgumentException("arg[0] of apply func can not be null");
        if (!(args[0] instanceof SProcedure))
            throw new WrongArgumentTypeException(SProcedure.class, args[0].getClass(), "apply");

        SProcedure procedure = (SProcedure) args[0];
        int length = args.length;
        SObject[] arguments = new SObject[length - 1];
        System.arraycopy(args, 1, arguments, 0, length - 1);

        return ApplyService.apply(procedure, arguments);
    }
}
