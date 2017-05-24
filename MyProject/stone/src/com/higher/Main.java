package com.higher;

import com.higher.util.Log;

public class Main {

    public static void main(String[] args) {
        Log.i("Hello world");
        Lexer lexer = new Lexer(new CodeDialog());

        for (Token read = lexer.read(); read != Token.EOF; ) {
            Log.i("Token:" + read);
            read = lexer.read();
        }
    }
}
