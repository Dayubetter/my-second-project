package com.dayu.demo18printstream;

import java.io.PrintStream;

public class PrintStreamDemo1 {
    public static void main(String[] args) {
        // 打印流的使用
        try (
                PrintStream ps = new PrintStream("java_max/src/com/dayu/demo18printstream/test.txt");
                ) {

            ps.print(true);
            ps.print(false);
            ps.print(97);
            ps.print("ni");
            ps.print('a');
            ps.println(3.14);
            ps.println("aaa");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
