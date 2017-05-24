package com.lxf;

import stone.Lexer;

/**
 * Created by lxf on 2017/5/24.
 */
public class test {
    public static void main(String[] args) {
        print("hello world");
        int a = 023;
//        print(a);
        String b = "\"";
        String c = "\"\\\\\\\"\"";
        print(b);
        print(c);
        print(Lexer.regexPat);
    }

    private static void print(Object o) {
        System.out.println(o);
    }
}
