package org.klose.scheme.io;

import org.klose.scheme.exception.IllegalExpressionException;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SPort implements Iterator<String> {
    public static final String EOF = "#!eof";
    private static final String START_TOKEN = "(";
    private static final String END_TOKEN = ")";
    private static final String COMMENT_TOKEN = ";";
    private static final String STRING_TOKEN = "\"";
    private static final String QUOTE_TOKEN="'";

    private static final String pattern = "\\s*(,@" +
            "|[('`,)]" +
            "|\"(?:[\\\\].|[^\\\\\"])*\"" +
            "|;.*" +
            "|[^\\s('\"`,;)]*)(.*)";

    private static final Pattern r = Pattern.compile(pattern);

    private String line = "";

    private LineNumberReader reader;

    public SPort(InputStream in) {
        this.reader = new LineNumberReader(new InputStreamReader(in));
    }

    public SPort(Reader in) {
        if (in instanceof LineNumberReader)
            this.reader = (LineNumberReader) in;
        else
            this.reader = new LineNumberReader(in);
    }

    public SPort(String s) {
        reader = new LineNumberReader(new StringReader(s));
    }

    @Override
    public boolean hasNext() {
        String token = next();
        return !EOF.equals(token);
    }

    @Override
    public String next() {
        while (true) {
            // read line
            if (line.isEmpty()) {
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    line = "";
                    return EOF;
                }
            }

            // reach the end of stream
            if (line == null) {
                line = "";
                return EOF;
            }

            // get next token based on regular expression
            Matcher m = r.matcher(line);
            if (m.find()) {
                String token = m.group(1);
                line = m.group(2);
                if (!token.isEmpty() && !token.startsWith(COMMENT_TOKEN)) {
                    return token;
                }
            }
        }

    }

    public Object read() throws IllegalExpressionException {
        String token = next();
        if (EOF.equals(token))
            return EOF;
        else
            return readAhead(token);
    }

    private Object readAhead(String token) throws IllegalExpressionException {
        if (START_TOKEN.equals(token)) {
            List<Object> result = new LinkedList<>();
            while (true) {
                String nextToken = next();
                if (END_TOKEN.equals(nextToken))
                    return result;
                else
                    result.add(readAhead(nextToken));
            }
        } else if (END_TOKEN.equals(token)) {
            throw new IllegalExpressionException("not balanced parenthesis");
        } else if (QUOTE_TOKEN.equals(token)) {
            List<Object> result = new LinkedList<>();
            result.add("quote");
            result.add(read());
            return result;
        } else if (EOF.equals(token)) {
            throw new IllegalExpressionException("unexpected EOF in list");
        } else {
            return token;
        }
    }

//    private String parse(String token) {
//        if (token.startsWith("\"")) {
//            return token.substring(1, token.length() - 1);
//        }
//        return token;
//    }

    public static void main(String args[]) {
        String str = "   (define (append \"x y\") \r\n (if (null? x) y (cons (car x)  (append (cdr x) y)))) ";
        //String str = "\"hello world\"";
        System.out.println("line = " + str);
        SPort port = new SPort(str);

        try {
            Object x = port.read();
            System.out.println(x);
        } catch (IllegalExpressionException e) {
            e.printStackTrace();
        }
    }
}
