package org.klose.scheme;

import java.util.LinkedList;
import java.util.List;

public class SExpression {

    private String value;
    private List<SExpression> children = new LinkedList<>();
    private SExpression parent;

    public SExpression(String value, SExpression parent) {
        this.value = value;
        this.parent = parent;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        if (!children.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append("\n");
            for (SExpression e : children)
                builder.append(e).append(" ");
            return builder.toString();
        } else
            return value;

    }

    //@TODO
    public  SObject evalute(SContext context) {
        if(Utils.isInteger(value))
            return new SNumber(Long.valueOf(value));
        else
            return null;
    }
}
