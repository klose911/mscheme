package org.klose.scheme.builtin;

import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SPair;

public class PairFunc {
    public static SPair cons(SObject... args) {
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
