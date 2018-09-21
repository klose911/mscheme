package org.klose.scheme.type;

import junit.framework.Assert;
import org.junit.Test;

import static org.klose.scheme.constant.SConstant.NIL;

public class SPairTest {

    @Test
    public void compareNil() {
        Assert.assertEquals(NIL, new SPair(null, null));
    }

    @Test
    public void testCons() {
        SPair p = cons(new SNumber(100), new SBoolean(false));
        System.out.println(p);
    }

    @Test
    public void testList() {
        SPair p = list(new SNumber(100), new SBoolean(false));
        System.out.println(p.getCar());
        System.out.println(p.getCdr());
    }

    private SPair cons(SObject car, SObject cdr) {
        return new SPair(car, cdr);
    }

    public SPair list(SObject first, SObject second) {
        return cons(first, cons(second, NIL));
    }
}
