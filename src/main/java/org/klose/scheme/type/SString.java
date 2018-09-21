package org.klose.scheme.type;

public class SString extends SObject {
    public SString(String str) {
        super(str);
    }

    @Override
    public String toString() {
        return "\"" + str + "\"";
    }
}
