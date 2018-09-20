package org.klose.scheme.type;


import java.io.Serializable;

public class SObject implements Serializable {
    protected String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public SObject() {
    }

    public SObject(String str) {
        this.str = str;
    }
}
