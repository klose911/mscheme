package org.klose.scheme.utils;

import junit.framework.Assert;
import org.junit.Test;
import org.klose.scheme.model.SExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SParserTest {
    private final static Logger logger = LoggerFactory.getLogger(SParserTest.class);
    private final String STATEMENT = "( def x (if  \r\n ( > a 1   ) 1 2.4 ) )";
    @Test
    public void testTokenize() {
        logger.info(STATEMENT);
        List<String> tokens = SParser.tokenize(STATEMENT);
        Assert.assertNotNull(tokens);
        logger.info("result : {}", SParser.joinTokens(tokens));
        Assert.assertEquals(14, tokens.size());
    }

    @Test
    public void testParse() {
        SExpression root = SParser.parse(STATEMENT);
        Assert.assertEquals("", root.getStr());
        Assert.assertEquals(3, root.getChildren().size());
        infoLog(root);
    }

    private static void infoLog(SExpression root) {
        logger.info(root.toString());
        for(SExpression child: root.getChildren())
            infoLog(child);
    }


}
