package com.dayu.extendsconstructor;

public class Test2 {
    public static void main(String[] args) {
        // this(...)调用兄弟构造器
        // super 和 this 都要写在第一行，而且不能同时出现
        Student s=new Student("zh",14);
        System.out.println(s);
        Student s1=new Student("mu");
        System.out.println(s1);
    }
}
