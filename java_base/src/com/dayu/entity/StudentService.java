package com.dayu.entity;

public class StudentService {
    private Student s; // 用于记住将来要操作的学生对象
    public StudentService(Student s) {
        this.s = s;
    }

    // 提供方法：打印学生对象的总成绩
    public void printTotalScore(){
        System.out.println(s.getName() + "Total");
    }
    // 提供方法，打印学生的平均成绩
    public void printAverageScore(){
        System.out.println(s.getName() + "Average");
    }
}
