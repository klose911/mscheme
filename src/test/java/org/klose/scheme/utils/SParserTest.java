package org.klose.scheme.utils;

import org.junit.Test;
import org.klose.scheme.model.SExpression;

import static junit.framework.Assert.assertEquals;

public class SParserTest {
    @Test
    public void testParse() {
        String code = "(define (append x y) (if (null? x) y (cons (car x) (append (cdr x) y))))";
        SExpression e = SParser.parse(code);
        assertEquals(code, e.toString());
    }
}
