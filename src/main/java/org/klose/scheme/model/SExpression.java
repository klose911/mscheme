package org.klose.scheme.model;

import org.apache.commons.lang3.StringUtils;
import org.klose.scheme.type.SObject;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class SExpression implements SObject {

    private final String str;
    private final List<SExpression> children = new ArrayList<>();
    private final SExpression parent;

    public SExpression(String str, SExpression parent) {
        this.str = str;
        this.parent = parent;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String getValue() {
        return StringUtils.isEmpty(str) ? toString() : str;
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

    public boolean hasNoneChild() {
        return children.isEmpty();
    }
}
