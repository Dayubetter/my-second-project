package com.dayu.demo1exception;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExceptionDemo {
    public static void main(String[] args) {
        // 运行时异常：编译不报错，运行时异常
        // show();
        try {
            show1();
        } catch (ParseException e) {
            e.printStackTrace(); // 输出异常信息
        }
    }

    public static void show1() throws ParseException {
        String str = "2026-04-8 23:23:23";
        // 把字符串解析为Java中的一个日期对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Data data = (Data) sdf.parse(str);  // 编译时异常，提示异常
         System.out.println(data);
    }

    // 定义一个方法认识运行时异常
    public static void show() {
        int[] arr = {1,2,3};
        //System.out.println(arr[3]); // ArrayIndexOutOfBoundsException

        // System.out.println(10/0); // ArithmeticException

        // 空指针异常
        String str = null;
        System.out.println(str);
        System.out.println(str.length()); // NullPointerException
    }
}
