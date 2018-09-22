package org.klose.scheme.model;

import org.klose.scheme.type.SObject;
import org.klose.scheme.type.SPrimitive;

import java.util.Collections;
import java.util.List;

public class SProcedure implements SObject {
    private final SExpression body;
    private final List<String> parameters;
    private final SFrame environment;

    public SProcedure(final SExpression body, final List<String> parameters,
                      final SFrame environment) {
        this.body = body;
        this.parameters = parameters;
        this.environment = environment;
    }

    public SExpression getBody() {
        return body;
    }

    public List<String> getParameters() {
        return Collections.unmodifiableList(parameters);
    }

    public SFrame getEnvironment() {
        return environment;
    }

    public boolean isPrimitive() {
        return this instanceof SPrimitive;
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException();
    }
}
