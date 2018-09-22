package org.klose.scheme.type;

public class SNumber implements SObject {
    private final Number value;

    public SNumber(Number value) {
        if (value == null)
            throw new IllegalArgumentException("SNumber value can not be null");
        this.value = value;
    }

    @Override
    public Number getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
