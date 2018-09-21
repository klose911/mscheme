package org.klose.scheme.type;

import java.util.Objects;

public class SBoolean extends SObject {
    private final Boolean value;

    public SBoolean(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SBoolean)) return false;
        SBoolean sBoolean = (SBoolean) o;
        return Objects.equals(getValue(), sBoolean.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
