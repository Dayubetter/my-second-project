package com.dayu.capsulation;

public class Test {
    public static void main(String[] args) {
        // 封装的设计思想，学会合理隐藏合理暴露
        Student s1 = new Student();
        // s1.age = -19;
        s1.setAge(20);
    }
}
