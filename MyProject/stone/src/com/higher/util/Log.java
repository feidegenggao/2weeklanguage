package com.higher.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lxf on 2017/5/24.
 */
public class Log {
    public static void i(String message) {
        SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS ");
        String a2 = dateformat2.format(new Date());
        print(a2 + "\t" + message);
    }

    private static void print(String s) {
        System.out.println(s);
    }
}
