package org.klose.scheme.type;

public class SBoolean extends SObject {
    private final Boolean value;

    public SBoolean(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
