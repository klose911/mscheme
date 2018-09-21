package org.klose.scheme.model;

import org.klose.scheme.type.SPrimitive;
import org.klose.scheme.type.SObject;

import java.util.List;

public class SProcedure implements SObject {
    private final SExpression body;
    private final List<String> parameters;
    private final SEnvironment context;

    public SProcedure(SExpression body, List<String> parameters, SEnvironment context) {
        this.body = body;
        this.parameters = parameters;
        this.context = context;
    }

    public SExpression getBody() {
        return body;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public SEnvironment getContext() {
        return context;
    }

    public boolean isPrimitive() {
        return this instanceof SPrimitive;
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException();
    }
}
