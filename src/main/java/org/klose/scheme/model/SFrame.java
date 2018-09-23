package org.klose.scheme.model;

import org.klose.scheme.type.SObject;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * An environment is a sequence of frames,
 * where each frame is a table of bindings that associate variables with their corresponding values
 */
public class SFrame {
    private final Map<String, SObject> vars;
    private final SFrame parent;


    public SFrame(Map<String, SObject> vars, SFrame parent) {
        if (vars == null)
            throw new IllegalArgumentException("variable map of a frame can not be null");

        this.vars = vars;
        this.parent = parent;
    }


    /**
     *
     * @param var
     * @return
     */
    public SObject lookup(String var) {
        if (vars.containsKey(var))
            return vars.get(var);
        else if (parent != null)
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

    public SFrame extend(Map<String, SObject> vars) {
        return new SFrame(vars, this);
    }

    @Override
    public String toString() {
        return "SFrame{" +
                "vars=" + vars.entrySet().stream()
                .map(e -> "var \'" + e.getKey() + "\' value= \'"
                        + e.getValue() + '\'')
                .collect(Collectors.joining(System.lineSeparator())) +
                ", parent=" + parent +
                '}';
    }
}
