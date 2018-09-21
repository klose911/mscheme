package org.klose.scheme.type;

import org.klose.scheme.constant.SConstant;

import java.util.Objects;

public class SPair extends SObject {
    protected SObject car;
    protected SObject cdr;

    public SPair(SObject car, SObject cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    public SObject getCar() {
        return car;
    }

    public void setCar(SObject car) {
        this.car = car;
    }

    public SObject getCdr() {
        return cdr;
    }

    public void setCdr(SObject cdr) {
        this.cdr = cdr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SPair)) return false;
        SPair sPair = (SPair) o;
        return Objects.equals(getCar(), sPair.getCar()) &&
                Objects.equals(getCdr(), sPair.getCdr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCar(), getCdr());
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
}
