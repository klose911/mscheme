package org.klose.scheme.primitive;

import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.exception.WrongArgumentTypeException;
import org.klose.scheme.type.SList;
import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SPair;

public class PairFunc {
    public static SPair cons(SObject... args) throws WrongArgumentNumberException {
        if (args == null)
            throw new IllegalArgumentException("args can not be null");

        if (args.length != 2)
            throw new WrongArgumentNumberException(2, args.length, "cons");

        if (args[0] == null)
            throw new IllegalArgumentException("args0 can not be null");
        if (args[1] == null)
            throw new IllegalArgumentException("args1 can not be null");

        if (args[1] instanceof SList)
            return new SList(args[0], (SList) args[1]);
        else
            return new SPair(args[0], args[1]);
    }

    public static SObject car(SObject... args)
            throws WrongArgumentNumberException, WrongArgumentTypeException {
        if (args == null)
            throw new IllegalArgumentException("args can not be null");
        if (args.length != 1)
            throw new WrongArgumentNumberException(1, args.length, "car");

        if (args[0] == null)
            throw new IllegalArgumentException("arg0 can not be null");
        if (!(args[0] instanceof SPair))
            throw new WrongArgumentTypeException(SPair.class, args[0].getClass(), "car");

        SPair pair = (SPair) args[0];
        return pair.getCar();
    }

    public static SObject cdr(SObject... args)
            throws WrongArgumentNumberException, WrongArgumentTypeException {
        if (args == null)
            throw new IllegalArgumentException("args can not be null");
        if (args.length != 1)
            throw new WrongArgumentNumberException(1, args.length, "cdr");

        if (args[0] == null)
            throw new IllegalArgumentException("arg0 can not be null");
        if (!(args[0] instanceof SPair))
            throw new WrongArgumentTypeException(SPair.class, args[0].getClass(), "cdr");

        SPair pair = (SPair) args[0];
        return pair.getCdr();
    }

    private PairFunc() {
        throw new UnsupportedOperationException("illegal operator for PairFunc");
    }
}
