package com.higher.test;


import com.higher.Lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试练习正则表达式的示例
 * Created by lxf on 2017/5/24.
 */
public class test_regex {
    public static void main(String[] args) {
        print(Lexer.regexPat);
        Pattern pattern = Pattern.compile(Lexer.regexPat);

        String test = "int a = 2";
        print("test's length:" + test.length());
        Matcher matcher = pattern.matcher(test);

        boolean lookingAt = matcher.lookingAt();
//        boolean matches = matcher.matches();
        print("matcher start at :" +  matcher.start() + ", end :" + matcher.end());
        print("Looking at :" + lookingAt);
//        print("Matches:" + matches);

        print("group:" + matcher.group());
        print("group count:" + matcher.groupCount());
        int len = 0;

        print("matcher end:" +matcher.end());

    }

    private static void print(Object o) {
        System.out.println(o);
    }
}
