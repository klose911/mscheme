package org.klose.scheme.func;


import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

public class SNumberTest {

    @Test
    public void testIsNumber() {
        Number f = NumberUtils.createNumber("-98.231");
        Number i = NumberUtils.createNumber("-222");
        System.out.println(f.floatValue() + " " + f.doubleValue() + " " + f.longValue() + " " + f.intValue());
        System.out.println(i.floatValue() + " " + i.doubleValue() + " " + i.longValue() + " " + i.intValue());
        printType(f);
        printType(i);
    }

    private void printType(Number n) {
        if (n instanceof Integer)
            System.out.println("is Integer");
        else
            System.out.println("not Integer");

        if (n instanceof Float)
            System.out.println("is Float");
        else
            System.out.println("not Float");

        if (n instanceof Long)
            System.out.println("is Long");
        else System.out.println("not Long");

        if (n instanceof Integer)
            System.out.println("is Integer");
        else
            System.out.println("not Integer");
    }
}
