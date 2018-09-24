package org.klose.scheme.utils;

import org.apache.commons.lang3.StringUtils;
import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.model.SExpression;

import java.util.*;

/**
 * parse input into several SExpression
 *
 * (define add (lambda (x y) (+ x y)) -> SExpression S0
 *
 *                            +-----------+
 *                            |           |
 *                            |     S0    |
 *                            +-----+-----+
 *                                  |
 *      +--------------------------------------------------------+
 *      |                           |                            |
 * +----v-----+                +----v-----+                +-----v----+
 * |          |                |          |                |          |
 * | "define" |                |   "add"  |                |    S1    |
 * +----------+                +----------+                +----------+
 *                                                               |
 *                               +------------------------------------
 *                               |                  |                |
 *                          +----v-----+      +-----v----+     +-----v-----+
 *                          |          |      |          |     |           |
 *                          | "lambda" |      |   S11    |     |    S12    |
 *                          +----------+      +----------+     +-----------+
 *                                                  |                |
 *                      +----------------+-----------                +----------------+----------------+
 *                      |                |                           |                |                |
 *                 +----v-----+    +-----v------+              +-----v-----+    +-----v-----+    +-----v-----+
 *                 |          |    |            |              |           |    |           |    |           |
 *                 |   "x"    |    |     "y"    |              |    "+"    |    |    "x"    |    |    "y"    |
 *                 +----------+    +------------+              +-----------+    +-----------+    +-----------+
 */

public class SParser {
    private static final String START_TOKEN = "(";
    private static final String START_TOKEN_REPLACED = " ( ";
    private static final String END_TOKEN = ")";
    private static final String END_TOKEN_REPLACED = " ) ";

    /**
     * parse String into SExpression
     *
     * @param code string to parse
     * @return parsed SExpression
     * @throws IllegalExpressionException illegal S Expression
     */
    public static SExpression parse(String code) throws IllegalExpressionException {
        if (StringUtils.isEmpty(code))
            throw new IllegalArgumentException("unexpected EOF while reading");

        return parse(tokenize(code), null);
    }

    private static LinkedList<String> tokenize(String text) {
        assert StringUtils.isNotEmpty(text);

        LinkedList<String> tokens = new LinkedList<>();
        StringTokenizer tokenizer = new StringTokenizer(text.replace(START_TOKEN, START_TOKEN_REPLACED)
                .replace(END_TOKEN, END_TOKEN_REPLACED));
        while (tokenizer.hasMoreTokens())
            tokens.add(tokenizer.nextToken());

        return tokens;
    }


    private static SExpression parse(LinkedList<String> tokens, SExpression parent)
            throws IllegalExpressionException {
        assert (tokens != null);

        if (tokens.isEmpty())
            throw new IllegalExpressionException("unexpected EOF while reading");

        String lex = tokens.pop();
        SExpression expression;
        if (START_TOKEN.equals(lex)) {
            expression = new SExpression("", parent);
            // add child expression
            while (!END_TOKEN.equals(tokens.getFirst())) {
                expression.getChildren().add(parse(tokens, parent));
            }

            if (tokens.isEmpty())
                throw new IllegalExpressionException("not balanced parenthesis");
            tokens.pop(); // pop off ')'
        } else if (END_TOKEN.equals(lex))
            throw new IllegalExpressionException("unexpected )");
        else
            expression = new SExpression(lex, parent); // self-evaluate expression, like numbers, variables etc.

        return expression;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        List<String> lines = new ArrayList<String>();
        String lineNew;

        while (input.hasNextLine()) {
            lineNew = input.nextLine();
            if (lineNew.isEmpty()) {
                break;
            }
            System.out.println(lineNew);
            lines.add(lineNew);
        }

        System.out.println("Content of List<String> lines:");
        for (String string : lines) {
            System.out.println(string);
        }
    }
}
