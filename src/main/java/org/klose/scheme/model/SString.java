package org.klose.scheme.model;

/**
 * Created by klose on 10/5/16.
 */
public class SString extends SObject {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SString(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
