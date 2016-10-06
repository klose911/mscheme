package org.klose.scheme.model;


public class SBoolean extends SObject{

    private Boolean value;

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public SBoolean(Boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SBoolean{" +
                "value=" + value +
                '}';
    }
}
