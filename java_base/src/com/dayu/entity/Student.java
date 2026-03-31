package com.dayu.entity;

public class Student {
    // 私有成员变量
    private String name;
    private double chinese;
    private double math;

    // 提供一个有参
    public Student(String name, double chinese, double math) {
        this.name = name;
        this.chinese = chinese;
        this.math = math;
    }

    // 有了有参构造，无参会失效，需要手动补一个
    public Student() {
    }


    // 2.提供公开的getter/setter

    public String getName() {
        return name;
    }

    public double getChinese() {
        return chinese;
    }

    public double getMath() {
        return math;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChinese(double chinese) {
        this.chinese = chinese;
    }

    public void setMath(double math) {
        this.math = math;
    }
}
