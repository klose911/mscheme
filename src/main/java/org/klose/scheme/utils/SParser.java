package org.klose.scheme.utils;

import org.apache.commons.lang3.StringUtils;
import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.model.SExpression;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * parse input into several SExpression
 */
public class SParser {
    private static final String START_TOKEN = "(";
    private static final String START_TOKEN_REPLACED = " ( ";
    private static final String END_TOKEN = ")";
    private static final String END_TOKEN_REPLACED = " ) ";
    //private final static Logger logger = LoggerFactory.getLogger(SParser.class);

    private static List<String> tokenize(String text) {
        assert StringUtils.isNotEmpty(text);

        List<String> tokens = new LinkedList<>();
        StringTokenizer tokenizer = new StringTokenizer(text.replace(START_TOKEN, START_TOKEN_REPLACED)
                .replace(END_TOKEN, END_TOKEN_REPLACED));
        while (tokenizer.hasMoreTokens())
            tokens.add(tokenizer.nextToken());

        return tokens;
    }

    public static SExpression parse(String code) throws IllegalExpressionException {
        if (StringUtils.isEmpty(code))
            throw new IllegalArgumentException("unexpected EOF while reading");

        SExpression program = new SExpression("", null);
        SExpression current = program;
        for (String lex : tokenize(code)) {
            if (START_TOKEN.equals(lex)) {
                SExpression newNode = new SExpression("", current);
                current.getChildren().add(newNode);
                current = newNode;
            } else if (lex.equals(END_TOKEN)) {
                if (current.getParent() == null)
                    throw new IllegalExpressionException("unexpected )");

                current = current.getParent();
            } else {
                current.getChildren().add(new SExpression(lex, current));
            }
        }

        return program.getChildren().get(0);
    }

}
