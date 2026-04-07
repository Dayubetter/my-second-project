package com.dayu.innerclass;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Student {
    private String name;
    private int age;
    private int height;
    private String sex;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", sex='" + sex + '\'' +
                '}';
    }

    public Student(String name, int age, int height, String sex) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.sex = sex;
    }

}
