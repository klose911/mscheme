package org.klose.scheme.type;

import java.util.Objects;

public class SNumber extends SObject {
    private final Number value;

    public SNumber(Number value) {
        this.value = value;
    }

    public Number getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SNumber)) return false;
        SNumber sNumber = (SNumber) o;
        return Objects.equals(getValue(), sNumber.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
