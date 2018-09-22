package org.klose.scheme.type;

public class SString implements SObject {
    private final String value;

    public SString(String value) {
        if (value == null)
            throw new IllegalArgumentException("SString value can not be null");

        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "\"" + value + "\"";
    }


}
