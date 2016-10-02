package org.klose.scheme;

import java.util.List;


public class SList {

    private List<SObject> values;

    public List<SObject> getValues() {
        return values;
    }

    public void setValues(List<SObject> values) {
        this.values = values;
    }

    public SList(List<SObject> values) {
        this.values = values;
    }

}
