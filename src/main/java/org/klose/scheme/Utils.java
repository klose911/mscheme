package org.klose.scheme;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Utils {

    public static List<String> tokenize(String text) {
        List<String> tokens = new LinkedList<>();
        StringTokenizer tokenizer = new StringTokenizer(text.replace("(", " ( ").replace(")", " ) "));
        while (tokenizer.hasMoreTokens())
            tokens.add(tokenizer.nextToken());

        return tokens;
    }


    // Displays the lexes in a readable form.
    public static String prettyPrint(List<String> lexes) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (String l : lexes)
            builder.append("'").append(l).append("', ");
        builder.append("]");

        return builder.toString();
    }

    public static SExpression parse(String code) {
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
        return program;
    }

    public static void main(String[] args) {
//        System.out.println(prettyPrint(tokenize("a")));
//        System.out.println(prettyPrint(tokenize("(def a 3)")));
//        System.out.println(prettyPrint(tokenize("(begin (def a 3) (* a a))")));
//
//        System.out.println(parse("a"));
//        System.out.println(parse("(def a 3)"));
        System.out.println(parse("(begin (def a 3) (* a a))"));

    }

    /**
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     **/
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
