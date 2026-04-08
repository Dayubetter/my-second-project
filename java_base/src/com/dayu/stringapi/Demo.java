package com.dayu.stringapi;

public class Demo {
    public static void main(String[] args) {
        String s1 = "hello";  // 放在字符串常量池，相同只放一份
        String s4 = new String("hello");
        System.out.println(s1.length());

        // 通过构造器初始化对象
        String s2 = new String();
        System.out.println(s2); // 空字符串

        String s3 = new String("hello"); // 放在堆中，每new一次，产生一个
        System.out.println(s3);

        char[] chars = {'h', 'e', 'l', 'l', 'o'};
        String s5 = new String(chars);
        System.out.println(s5);

        byte[] bytes = {104, 101, 108, 108, 111};
        String s6 = new String(bytes);
        System.out.println(s6);
    }
}
