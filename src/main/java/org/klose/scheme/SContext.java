package org.klose.scheme;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class SContext {
    private SContext parent;
    private Map<String, SObject> envMap;

    private Map<String, BiFunction<SExpression[], SContext, SObject>> builtinProcedureMap;

    public SContext(SContext parent) {
        this.parent = parent;
        this.envMap = new LinkedHashMap<String, SObject>();
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
