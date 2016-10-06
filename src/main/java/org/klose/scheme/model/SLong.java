package org.klose.scheme.model;

public class SLong extends SObject {
    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public SLong(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
