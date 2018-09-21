package org.klose.scheme.model;

import org.klose.scheme.type.SObject;

import java.util.Collections;
import java.util.Map;

public class SEnvironment {
    private final Map<String, SObject> vars;
    private final SEnvironment parent;


    public SEnvironment(Map<String, SObject> vars, SEnvironment parent) {
        this.vars = vars;
        this.parent = parent;
    }

    public Map<String, SObject> getVars() {
        return Collections.unmodifiableMap(vars);
    }

    public SEnvironment getParent() {
        return parent;
    }

    public SObject lookup(String var) {
        if (vars.containsKey(var))
            return vars.get(var);
        else if(parent != null)
            return parent.lookup(var);
        else
            throw new RuntimeException("not found variable");
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
