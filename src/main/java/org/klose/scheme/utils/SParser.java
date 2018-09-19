package org.klose.scheme.utils;

import org.klose.scheme.model.SExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * parsing input into several SExpression
 */
public class SParser {
    private final static Logger logger = LoggerFactory.getLogger(SParser.class);

    public static List<String> tokenize(String text) {
        logger.info("start tokenize input");
        logger.debug("[input statement : {} ]", text);
        List<String> tokens = new LinkedList<>();
        StringTokenizer tokenizer = new StringTokenizer(text.replace("(", " ( ").replace(")", " ) "));
        while (tokenizer.hasMoreTokens())
            tokens.add(tokenizer.nextToken());

        logger.info("finish tokenize");
        logger.debug("tokens : {}", joinTokens(tokens));

        return tokens;
    }

    public static SExpression parse(String code) {
        logger.info("start parser s-expression");
        logger.debug("[s-expression : {}", code);

        SExpression program = new SExpression("", null);
        SExpression current = program;
        for (String lex : tokenize(code)) {
            if (lex.equals("(")) {
                SExpression newNode = new SExpression("", current);
                current.getChildren().add(newNode);
                current = newNode;
            } else if (lex.equals(")")) {
                current = current.getParent();
            } else {
                current.getChildren().add(new SExpression(lex, current));
            }
        }
        SExpression parsed = program.getChildren().get(0);

        logger.info("finish parsing");
        traceLog(parsed);
        return parsed;
    }

    public static String joinTokens(List<String> lexes) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (String l : lexes)
            builder.append("'").append(l).append("', ");
        builder.append("]");
        return builder.toString();
    }

    private static void traceLog(SExpression root) {
        logger.trace(root.toString());
        for(SExpression child: root.getChildren())
            traceLog(child);
     }

}
