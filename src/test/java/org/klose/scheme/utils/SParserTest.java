package org.klose.scheme.utils;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.model.SExpression;

public class SParserTest {

    @Test
    public void testTokenize() {
        String code = "(define (append x y) (if (null? x) y (cons (car x) (append (cdr x) y))))\n";
        for (String s : SParser.tokenize(code))
            System.out.print(" \"" + s + "\" ");
    }

    @Test
    public void testParse() {
        String code = "(define (append x y) (if (null? x) y (cons (car x) (append (cdr x) y))))\n";
        SExpression e = SParser.parse(code);
        String printed = "['define',['append','x','y'],['if',['null?','x'],'y',['cons',['car','x'],['append',['cdr','x'],'y']]]]";
        Assert.assertEquals(printed, e.toString());
    }
}
