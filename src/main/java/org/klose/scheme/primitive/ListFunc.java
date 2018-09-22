package org.klose.scheme.primitive;

import org.klose.scheme.constant.SConstant;
import org.klose.scheme.exception.WrongArgumentNumberException;
import org.klose.scheme.type.SBoolean;
import org.klose.scheme.type.SList;
import org.klose.scheme.type.SObject;

import static org.klose.scheme.constant.SConstant.NIL;

public class ListFunc {

    public static SList list(SObject... args) {
        if (args == null)
            throw new IllegalArgumentException("list args can not be null");

        if (args.length == 0)
            return NIL;
        else if (args.length == 1)
            return new SList(args[0], NIL);
        else {
            return new SList(args[0], list(restArgs(args)));
        }
    }

    public static SBoolean isNull(SObject... args) throws WrongArgumentNumberException {
        if (args == null)
            throw new IllegalArgumentException("isNull args can not be null");
        if (args.length != 1)
            throw new WrongArgumentNumberException(1, args.length, "null?");

        if (args[0] instanceof SList) {
            SList args0 = (SList) args[0];
            return new SBoolean(SConstant.NIL.equals(args0));
        } else
            return SConstant.FALSE;

    }

    //@TODO optimize
    private static SObject[] restArgs(SObject... args) {
        final int length = args.length;
        if (length > 1) {
            SObject[] rest = new SObject[length - 1];
            for (int i = 1; i < length; i++) {
                rest[i - 1] = args[i];
            }
            return rest;
        }
        return new SObject[0];
    }

}
