package org.klose.scheme;

public class SNumber extends SObject {
    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public SNumber(Long value) {
        this.value = value;
    }

}
