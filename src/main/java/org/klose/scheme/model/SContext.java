package org.klose.scheme.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class SContext {
    private SContext parent;
    private Map<String, SObject> envMap;

    public SContext(SContext parent) {
        this.parent = parent;
        this.envMap = new LinkedHashMap<>();
    }

    public SObject find(String name) {
        SContext current = this;
        while (current != null) {
            if (current.envMap.containsKey(name)) {
                return current.envMap.get(name);
            }
            current = current.parent;
        }
        throw new RuntimeException(name + " is not defined.");
    }

    public SObject define(String name, SObject value) {
        this.envMap.put(name, value);
        return value;
    }
}
