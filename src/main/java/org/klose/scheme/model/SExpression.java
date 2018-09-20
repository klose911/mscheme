package org.klose.scheme.model;

import org.klose.scheme.type.SObject;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class SExpression extends SObject {

    private String str;
    private List<SExpression> children = new ArrayList<>();
    private SExpression parent;

    public SExpression(String str, SExpression parent) {
        this.str = str;
        this.parent = parent;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public List<SExpression> getChildren() {
        return children;
    }

    public void setChildren(List<SExpression> children) {
        this.children = children;
    }

    public SExpression getParent() {
        return parent;
    }

    public void setParent(SExpression parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (children.isEmpty())
            builder.append("'").append(str).append("'");
        else {
            builder.append("[");
            builder.append(children.stream()
                    .map(SExpression::toString)
                    .collect(joining(",")));
            builder.append("]");
        }
        return builder.toString();
    }

    public boolean hasNoneChild() {
        return children == null || children.isEmpty();
    }
}
