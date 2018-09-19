package org.klose.scheme.model;

import org.klose.scheme.type.SObject;

import java.util.HashMap;
import java.util.Map;

public class SFrame {

    private Map<String, SObject> vars = new HashMap<>();

    public SFrame() {
    }


    public Map<String, SObject> getVars() {
        return vars;
    }

    public void setVars(Map<String, SObject> vars) {
        this.vars = vars;
    }

    public void bind(String var, SObject val) {
        vars.put(var, val);
    }

    public SObject find(String var) {
        return vars.get(var);
    }

    public boolean contains(String var) {
        return vars.containsKey(var);
    }
}
