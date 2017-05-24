package com.higher.test;

/**
 * Created by lxf on 2017/5/24.
 */
public class Convert_string_to_literal {
    public static void main(String[] args) {
        print("hello world");


    }

    public static String convertStringToListeral(String s) {
        StringBuilder stringBuilder = new StringBuilder();

        int len = s.length() - 1;
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            if (c == '\\' && i + 1 < len) {
                char c2 = s.charAt(i + 1);
                if (c2 == '"' || c2 == '\\') {
                    c = c2;i++;
                } else if (c2 == 'n') {
                    c = '\n';i++;
                } else if (c2 == 't') {
                    c = '\t';i++;
                }
                //其他情况，不用跳过字符
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    private static void print(Object o) {
        System.out.println(o);
    }
}
