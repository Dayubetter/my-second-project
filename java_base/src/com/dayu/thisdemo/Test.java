package com.dayu.thisdemo;

public class Test {
    public static void main(String[] args) {
        // this指针，搞清楚this关键字的应用场景
        Student s1 = new Student();
        s1.name = "s1";
        s1.print();
        System.out.println(s1);
        System.out.println("========");

        Student s2 = new Student();
        s2.print();
        System.out.println(s2);

        System.out.println("===========");
        // this主要用来解决变量名冲突
        Student s3 = new Student();
        s3.name = "s3";
        s3.printHobby("singing");
    }
}
