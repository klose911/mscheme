package org.klose.scheme.utils;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.model.SExpression;

public class SParserTest {
    @Test
    public void testParse() {
        String code = "(define (append x y) (if (null? x) y (cons (car x) (append (cdr x) y))))";
        SExpression e = SParser.parse(code);
        Assert.assertEquals(code, e.toString());
    }
}
