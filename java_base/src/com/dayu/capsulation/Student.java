package com.dayu.capsulation;

public class Student {
    String name;
    private int age;
    private double chinese;
    private double math;

    // 用get和set取值和赋值
    public void setAge(int age) {
        if (age >= 0 && age <= 100) {
            this.age = age;
        }
    }

    public void printAllScore(){
        System.out.println(name + " Score is" +(chinese+math));
    }

    public void printAverageScore(){
        System.out.println(name + " AverageScore is" +(chinese+math)/2);
    }
}
