package org.klose.scheme.type;

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
}
