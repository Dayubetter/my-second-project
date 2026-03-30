package com.dayu.constructor;

public class Student {
    String name;
    int age;
    char gender;
    // 无参
    public Student() {

    }
    public Student(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
