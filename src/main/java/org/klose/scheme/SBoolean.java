package org.klose.scheme;


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

}
