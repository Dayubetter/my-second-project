package com.dayu.constructor;

public class Test {
    public static void main(String[] args) {
        // 类的构造器，特点常见应用场景
        Student s=new Student();
        s.name="<UNK>";
        s.age=10;
        s.gender='M';
        Student s1=new Student("UNK", 10, 'M');
        System.out.println(s1.name);
        System.out.println(s1.age);
        System.out.println(s1.gender);
    }
}
