package com.higher;

import com.higher.test.Convert_string_to_literal;
import com.higher.util.Log;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 词法分析器
 * 使用Java的正则表达式进行词法分析
 * Created by lxf on 2017/5/24.
 */
public class Lexer {
    public static final String regexPat
            = "\\s*((//.*)|([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")"
            + "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";

    private Pattern pattern = Pattern.compile(regexPat);
    private LineNumberReader reader;

    public Lexer(Reader reader) {
        this.reader = new LineNumberReader(reader);
    }

    private ArrayList<Token> queue = new ArrayList<>();

    public Token read() {
        if (fillQueue(0)) {
            return queue.remove(0);
        }
        return Token.EOF;
    }

    public Token peek(int i) {
        if (fillQueue(i)) {
            return queue.get(0);
        }
        return Token.EOF;
    }

    private boolean fillQueue(int i) {
        while (i >= queue.size()) {
            if (!readLine()) {
                return false;
            }
        }
        return true;
    }

    private boolean readLine() {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (line == null) {
            return false;
        }
        int lineNumber = reader.getLineNumber();
        Matcher matcher = pattern.matcher(line);

        int end = 0;
        while (end < line.length()) {
            if (matcher.lookingAt()) {
                addToken(lineNumber, matcher);
                end = matcher.end();
                matcher.region(end, line.length());
            } else {
                throw new StoneException("Bad token at " + lineNumber + " line, this line content:" +
                        line);
            }
        }

        queue.add(new IdToken(lineNumber, Token.EOL));
        return true;
    }

    private void addToken(int lineNumber, Matcher matcher) {
        if (matcher.group(1) == null) {
            Log.i("这是一个空白字符的token");
            return;
        }
        String matcherGroup = matcher.group(1);
        if (matcher.group(4) != null) {
            StrToken strToken = new StrToken(lineNumber, Convert_string_to_literal.convertStringToListeral(matcherGroup));
            Log.i("String's len:" + strToken.getValue().length());
            queue.add(strToken);
            return;
        }

        if (matcher.group(3) != null) {
            NumToken numToken = new NumToken(lineNumber, Integer.valueOf(matcherGroup));
            queue.add(numToken);
            return;
        }

        if (matcher.group(2) != null) {
            Log.i("这是一行注释，不是token，内容是：" + matcherGroup);
        } else {
            IdToken idToken = new IdToken(lineNumber, matcherGroup);
            queue.add(idToken);
        }
    }

    public static class NumToken extends Token {
        private int value;

        public NumToken(int lineNumber, int number) {
            super(lineNumber);
            value = number;
        }

        @Override
        public boolean isNumber() {
            return true;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "NumToken @:" + getLineNumber() + " line, value:"  + value;
        }
    }

    public static class IdToken extends Token {
        private String value;

        public IdToken(int lineNumber, String id) {
            super(lineNumber);

            value = id;
        }

        @Override
        public boolean isIdntifier() {
            return true;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "IdToken @:" + getLineNumber() + " line, value:" + value;
        }
    }

    public static class StrToken extends Token {
        private String value;

        public StrToken(int lineNumber, String string) {
            super(lineNumber);

            value = string;
        }

        @Override
        public boolean isString() {
            return true;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "StrToken @:" + getLineNumber() + " line, value:"  + value;
        }
    }

}
