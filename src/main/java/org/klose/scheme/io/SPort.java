package org.klose.scheme.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SPort implements Iterator<String> {
  public static final String EOF = "#!eof";

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
    return reader != null;
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
        }
      }

      // reach the end of stream
      if (line == null) {
        line = "";
        try {
          reader.close();
          reader = null;
        } catch (IOException e) {
          e.printStackTrace();
        }
        //TODO implement symbol later
        return EOF;
      }

      // get next token based on regular expression
      Matcher m = r.matcher(line);
      if (m.find()) {
        String token = m.group(1);
        line = m.group(2);
        if (!token.isEmpty() && !token.startsWith(";")) {
          return token;
        }
      }
    }

  }

  public static void main(String args[]) {
    String str = "   (,@define (append \"x y\") \r\n (if (null? x) y (cons (car x)  (append (cdr x) y)))) ";
    System.out.println("line = " + str);
    SPort port = new SPort(str);
    String token;
    while (port.hasNext()) {
      System.out.println("token = " + port.next());
    }
  }
}
