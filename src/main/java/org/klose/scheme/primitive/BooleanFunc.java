package org.klose.scheme.primitive;

import org.klose.scheme.constant.SConstant;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.exception.WrongArgumentTypeException;
import org.klose.scheme.type.SBoolean;
import org.klose.scheme.type.SObject;

public class BooleanFunc {
    public static SBoolean and(SObject... args)
            throws WrongArgumentNumberException, WrongArgumentTypeException {
        if (args == null)
            throw new IllegalArgumentException("args can not null");
        if (args.length != 2)
            throw new WrongArgumentNumberException(2, args.length, "and");

        SObject arg0 = args[0];
        if (arg0 == null)
            arg0 = SConstant.FALSE;
        if (!(arg0 instanceof SBoolean))
            throw new WrongArgumentTypeException(SBoolean.class, arg0.getClass(), "and");

        SObject arg1 = args[1];
        if (arg1 == null)
            arg1 = SConstant.FALSE;
        if (!(arg1 instanceof SBoolean))
            throw new WrongArgumentTypeException(SBoolean.class, arg1.getClass(), "and");

        return new SBoolean(((SBoolean) arg0).getValue() && ((SBoolean) arg1).getValue());
    }

    public static SBoolean or(SObject... args)
            throws WrongArgumentNumberException, WrongArgumentTypeException {
        if (args == null)
            throw new IllegalArgumentException("args can not null");
        if (args.length != 2)
            throw new WrongArgumentNumberException(2, args.length, "and");
        SObject arg0 = args[0];
        if (arg0 == null)
            arg0 = SConstant.FALSE;
        if (!(arg0 instanceof SBoolean))
            throw new WrongArgumentTypeException(SBoolean.class, arg0.getClass(), "or");

        SObject arg1 = args[1];
        if (arg1 == null)
            arg1 = SConstant.FALSE;
        if (!(arg1 instanceof SBoolean))
            throw new WrongArgumentTypeException(SBoolean.class, arg1.getClass(), "or");

        return new SBoolean(((SBoolean) arg0).getValue() || ((SBoolean) arg1).getValue());
    }

    public static SBoolean not(SObject... args)
            throws WrongArgumentTypeException, WrongArgumentNumberException {
        if (args == null)
            throw new IllegalArgumentException("args can not null");
        if (args.length != 1)
            throw new WrongArgumentNumberException(1, args.length, "equalsTo");

        SObject arg0 = args[0];
        if (arg0 == null)
            arg0 = SConstant.FALSE;
        if (!(arg0 instanceof SBoolean))
            throw new WrongArgumentTypeException(SBoolean.class, arg0.getClass(), "not");

        return new SBoolean(!((SBoolean) arg0).getValue());
    }

    public static SBoolean equalsTo(SObject... args)
            throws WrongArgumentNumberException {
        if (args == null)
            throw new IllegalArgumentException("args can not be null");

        if (args.length != 2)
            throw new WrongArgumentNumberException(2, args.length, "equalsTo");

        return new SBoolean(args[0].getValue().equals(args[1].getValue()));
    }

    private BooleanFunc() {
        throw new UnsupportedOperationException("illegal constructor for BooleanFunc");
    }
}
