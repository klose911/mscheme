package org.klose.scheme.model;

import org.klose.scheme.type.SObject;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;


public class SExpression implements SObject {

    private final String str;
    private final List<SExpression> children = new ArrayList<>();
    private final SExpression parent;

    public SExpression(String str, SExpression parent) {
        if (str == null)
            throw new IllegalArgumentException("expression str can not be null");

        this.str = str;
        this.parent = parent;
    }


    @Override
    public String getValue() {
        return children.isEmpty() ? str : toString();
    }


    public List<SExpression> getChildren() {
        return children;
    }


    public SExpression getParent() {
        return parent;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (children.isEmpty())
            builder.append(str);
        else {
            builder.append("(");
            builder.append(children.stream()
                    .map(SExpression::toString)
                    .collect(joining(" ")));
            builder.append(")");
        }
        return builder.toString();
    }

    public boolean isSelfEval() {
        return children.isEmpty();
    }
}
