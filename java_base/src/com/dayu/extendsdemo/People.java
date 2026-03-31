package com.dayu.extendsdemo;

public class People {
    // 基类
    // 继承的好处：1.代码的复用 2.减少重复代码
    private String name;
    private char gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
