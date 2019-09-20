package org.klose.scheme.utils;

import org.apache.commons.lang3.StringUtils;
import org.klose.scheme.exception.IllegalExpressionException;
import org.klose.scheme.io.SPort;
import org.klose.scheme.model.SExpression;

import java.util.List;

/**
 * parse input into several SExpression
 * <p>
 * (define add (lambda (x y) (+ x y)) -> SExpression S0
 * <p>
 * +-----------+
 * |           |
 * |     S0    |
 * +-----+-----+
 * |
 * +--------------------------------------------------------+
 * |                           |                            |
 * +----v-----+                +----v-----+                +-----v----+
 * |          |                |          |                |          |
 * | "define" |                |   "add"  |                |    S1    |
 * +----------+                +----------+                +----------+
 * |
 * +------------------------------------
 * |                  |                |
 * +----v-----+      +-----v----+     +-----v-----+
 * |          |      |          |     |           |
 * | "lambda" |      |   S11    |     |    S12    |
 * +----------+      +----------+     +-----------+
 * |                |
 * +----------------+-----------                +----------------+----------------+
 * |                |                           |                |                |
 * +----v-----+    +-----v------+              +-----v-----+    +-----v-----+    +-----v-----+
 * |          |    |            |              |           |    |           |    |           |
 * |   "x"    |    |     "y"    |              |    "+"    |    |    "x"    |    |    "y"    |
 * +----------+    +------------+              +-----------+    +-----------+    +-----------+
 */

public class SParser {
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

        return parse(new SPort(code));
    }

    /**
     * parse input into SExpression
     *
     * @param port io port
     * @return parsed SExpression
     * @throws IllegalExpressionException illegal S Expression
     */
    public static SExpression parse(SPort port) throws IllegalExpressionException {
        if (port == null) {
            throw new IllegalArgumentException("port is closed ");
        }

        return parse(port.read(), null);
    }

    private static SExpression parse(Object x, SExpression parent) throws IllegalExpressionException {
        if (x instanceof String) {
            return new SExpression((String) x, parent);
        } else if (x instanceof List) {
            List<Object> l = (List<Object>) x;
            SExpression expression = new SExpression("", parent);
            for (Object o : l) {
                expression.getChildren().add(parse(o, parent));
            }
            return expression;
        }
        throw new IllegalExpressionException(" illegal class instance of parsed tokens");
    }

    public static void main(String[] args) throws IllegalExpressionException {
      String code = "('define (append x y) \r\n (if (null? x) y (cons (car x) (append (cdr x) y))))";
      SExpression e = SParser.parse(code);
      System.out.println(e.toString());
    }

    private SParser() {
        throw new UnsupportedOperationException("illegal constructor for SParser");
    }
}
