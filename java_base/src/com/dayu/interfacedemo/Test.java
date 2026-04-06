package com.dayu.interfacedemo;

public class Test {
    public static void main(String[] args) {
        People people = new Student();
        Driver driver = new Student(); // 多态
        Boy boy = new Student();

        // 接口可以实现面向接口编程，更利于解耦
        Driver driver1 = new Student();
        Boy boy1 = new Teacher();
    }
}

interface Driver {}
interface Boy {}
class People{}
class Student extends People implements Driver, Boy { }

class Teacher implements Driver, Boy {}