package org.klose.scheme.type;

import org.klose.scheme.constant.SConstant;

public class SList extends SPair {
    public SList(SObject first, SList rest) {
        super(first, rest);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public SList getCdr() {
        return (SList) cdr;
    }

    @Override
    public String toString() {
        SObject first = car;
        StringBuilder builder = new StringBuilder("(");
        if (first != null)
            builder = builder.append(first);

        builder.append(cdr).append(")");
        return builder.toString();
    }

    public static void main(String[] args) {
        SList list0 = new SList(new SNumber(100d), SConstant.NIL);
        System.out.println(list0);
        SList list1 = new SList(new SNumber(100),
                new SList(new SNumber(200L), SConstant.NIL));
        System.out.println(list1);
        SList list2 = new SList(list0, list1);
        System.out.println(list2);
    }
}
