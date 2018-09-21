package org.klose.scheme.primitive;

import org.klose.scheme.type.SList;
import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SPair;

public class PairFunc {
    public static SPair cons(SObject... args) {
        if (args[1] instanceof SList)
            return new SList(args[0], (SList) args[1]);
        else
            return new SPair(args[0], args[1]);
    }

    public static SObject car(SObject... args) {
        SPair pair = (SPair) args[0];
        return pair.getCar();
    }

    public static SObject cdr(SObject... args) {
        SPair pair = (SPair) args[0];
        return pair.getCdr();
    }

}
