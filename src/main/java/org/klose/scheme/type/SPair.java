package org.klose.scheme.type;

import org.klose.scheme.constant.SConstant;

public class SPair implements SObject {
    final SObject car;
    final SObject cdr;

    public SPair(SObject car, SObject cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    public SObject getCar() {
        return car;
    }

    public SObject getCdr() {
        return cdr;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(").append(car);
        if (!cdr.equals(SConstant.NIL))
            builder.append(" . ").append(cdr);
        builder.append(")");
        return builder.toString();
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException("");
    }
}
