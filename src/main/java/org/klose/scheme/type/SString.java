package org.klose.scheme.type;

public class SString implements SObject {
    private final String value;

    public SString(String value) {
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
