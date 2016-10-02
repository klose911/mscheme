package org.klose.scheme;

import java.util.LinkedList;
import java.util.List;

public abstract class SProcedure extends SObject {

    private SExpression body;
    private List<String> parameters;
    private SContext context;


    public SProcedure(SExpression body, List<String> parameters, SContext context) {
        this.body = body;
        this.parameters = parameters;
        this.context = context;
    }

    public SObject evalute() {
        if (isPartial())
            return this;
        else
            return body.evalute(context);
    }

    private List<String> computeFilledParameters() {
        List<String> computed = new LinkedList<>();
        for (String para : parameters) {
            if (context.find(para) != null)
                computed.add(para);
        }
        return computed;
    }

    private Boolean isPartial() {
        return computeFilledParameters().size() < parameters.size();
    }


}
