package com.higher;

/**
 * Created by lxf on 2017/5/24.
 */
public class StoneException extends RuntimeException {
    public StoneException(String s) {
        super(s);
    }

    public static void main(String[] args) {
        print("hello world");
    }

    private static void print(Object o) {
        System.out.println(o);
    }
}
