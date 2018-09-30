package org.klose.scheme.primitive;

import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.exception.WrongArgumentTypeException;
import org.klose.scheme.type.SBoolean;
import org.klose.scheme.type.SNumber;
import org.klose.scheme.type.SObject;

public class CompareFunc {

    public static SBoolean greater(SObject... args)
            throws WrongArgumentNumberException, WrongArgumentTypeException {
        if (args == null)
            throw new IllegalArgumentException("args can not null");
        if (args.length != 2)
            throw new WrongArgumentNumberException(2, args.length, ">");

        SObject arg0 = args[0];
        if (arg0 == null)
            throw new IllegalArgumentException("arg0 can not be null");
        if (!(arg0 instanceof SNumber))
            throw new WrongArgumentTypeException(SNumber.class, arg0.getClass(), ">");

        SObject arg1 = args[1];
        if (arg1 == null)
            throw new IllegalArgumentException("arg1 can not be null");
        if (!(arg1 instanceof SNumber))
            throw new WrongArgumentTypeException(SNumber.class, arg1.getClass(), ">");

        return new SBoolean(((Number) arg0.getValue()).doubleValue() >
                ((Number) arg1.getValue()).doubleValue());
    }

    public static SBoolean less(SObject... args) throws WrongArgumentNumberException, WrongArgumentTypeException {
        if (args == null)
            throw new IllegalArgumentException("args can not null");
        if (args.length != 2)
            throw new WrongArgumentNumberException(2, args.length, "<");

        SObject arg0 = args[0];
        if (arg0 == null)
            throw new IllegalArgumentException("arg0 can not be null");
        if (!(arg0 instanceof SNumber))
            throw new WrongArgumentTypeException(SNumber.class, arg0.getClass(), "<");

        SObject arg1 = args[1];
        if (arg1 == null)
            throw new IllegalArgumentException("arg1 can not be null");
        if (!(arg1 instanceof SNumber))
            throw new WrongArgumentTypeException(SNumber.class, arg1.getClass(), "<");

        return new SBoolean(((Number) arg0.getValue()).doubleValue() <
                ((Number) arg1.getValue()).doubleValue());
    }

    public static SBoolean eq(SObject... args) throws WrongArgumentNumberException, WrongArgumentTypeException {
        if (args == null)
            throw new IllegalArgumentException("args can not null");
        if (args.length != 2)
            throw new WrongArgumentNumberException(2, args.length, "=");

        SObject arg0 = args[0];
        if (arg0 == null)
            throw new IllegalArgumentException("arg0 can not be null");
        if (!(arg0 instanceof SNumber))
            throw new WrongArgumentTypeException(SNumber.class, arg0.getClass(), "=");

        SObject arg1 = args[1];
        if (arg1 == null)
            throw new IllegalArgumentException("arg1 can not be null");
        if (!(arg1 instanceof SNumber))
            throw new WrongArgumentTypeException(SNumber.class, arg1.getClass(), "=");

        return new SBoolean(((Number) arg0.getValue()).doubleValue() ==
                ((Number) arg1.getValue()).doubleValue());
    }

    private CompareFunc() {
        throw new UnsupportedOperationException("illegal constructor for ComapreFunc");
    }
}
