package org.klose.scheme.model;

import org.klose.scheme.type.SObject;

import java.util.HashMap;
import java.util.Map;

public class SEnvironment {
    private Map<String, SObject> vars = new HashMap<>();
    private SEnvironment parent;

    public SEnvironment() {
    }

    public SEnvironment(Map<String, SObject> vars, SEnvironment parent) {
        this.vars = vars;
        this.parent = parent;
    }

    public Map<String, SObject> getVars() {
        return vars;
    }

    public void setVars(Map<String, SObject> vars) {
        this.vars = vars;
    }

    public SEnvironment getParent() {
        return parent;
    }

    public void setParent(SEnvironment parent) {
        this.parent = parent;
    }

    public SObject lookup(String var) {
        if (vars.containsKey(var))
            return vars.get(var);
        else
            return parent.lookup(var);
    }

    public void define(String var, SObject val) {
        vars.put(var, val);
    }

    public void assign(String var, SObject val) {
        if (vars.containsKey(var)) {
            vars.put(var, val);
        } else {
            if (parent != null)
                parent.assign(var, val);
            else
                throw new RuntimeException("not found assigned variable");
        }
    }

    public SEnvironment extend(Map<String, SObject> vars) {
        return new SEnvironment(vars, this);
    }
}
