package org.klose.scheme.model;


public class SFloat extends SObject {
    private Float value;

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public SFloat(Float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
