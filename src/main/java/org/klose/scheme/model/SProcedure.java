package org.klose.scheme.model;

import org.apache.commons.lang3.StringUtils;
import org.klose.scheme.type.SObject;

import java.util.List;

public class SProcedure extends SObject {

    private SExpression body;
    private List<String> parameters;
    private SEnvironment context;

    public SProcedure() {
    }

    public SProcedure(String str) {
        super(str);
    }

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

    public void setBody(SExpression body) {
        this.body = body;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public void setContext(SEnvironment context) {
        this.context = context;
    }

    public boolean isPrimitive() {
        return !StringUtils.isEmpty(str);
    }
}
