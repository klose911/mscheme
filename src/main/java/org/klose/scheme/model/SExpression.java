package org.klose.scheme.model;

import org.klose.scheme.utils.Utils;

import java.util.LinkedList;
import java.util.List;

public class SExpression {

    private String str;
    private List<SExpression> children = new LinkedList<>();
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
        if(str.equals("")) {
            StringBuilder builder = new StringBuilder();
            builder.append("(");
            for(SExpression child: children)
                builder.append(" ").append(child).append(" ");
            builder.append(")");
            return builder.toString();
        } else
            return str;
    }

    //@TODO
    public SObject evalute(SContext context) {
        if (Utils.isLong(str))
            return new SLong(Long.valueOf(str));
        else
            return null;
    }

}
