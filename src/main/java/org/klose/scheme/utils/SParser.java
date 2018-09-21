package org.klose.scheme.utils;

import org.klose.scheme.model.SExpression;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * parse input into several SExpression
 */
public class SParser {
    //private final static Logger logger = LoggerFactory.getLogger(SParser.class);

    public static List<String> tokenize(String text) {
        List<String> tokens = new LinkedList<>();
        StringTokenizer tokenizer = new StringTokenizer(text.replace("(", " ( ").replace(")", " ) "));
        while (tokenizer.hasMoreTokens())
            tokens.add(tokenizer.nextToken());

        return tokens;
    }

    public static SExpression parse(String code) {
        SExpression program = new SExpression("", null);
        SExpression current = program;
        for (String lex : tokenize(code)) {
            if ("(".equals(lex)) {
                SExpression newNode = new SExpression("", current);
                current.getChildren().add(newNode);
                current = newNode;
            } else if (lex.equals(")")) {
                current = current.getParent();
            } else {
                current.getChildren().add(new SExpression(lex, current));
            }
        }

        return program.getChildren().get(0);
    }

}
