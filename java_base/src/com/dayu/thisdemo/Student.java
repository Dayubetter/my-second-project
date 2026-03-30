package com.dayu.thisdemo;

public class Student {
    String name;

    public void print(){
        // this 是一个变量，在一个方法中，用于拿到当前对象
        // 哪个对象调用这个方法，this就拿到哪个对象
        System.out.println(this);
        System.out.println(this.name);
    }
    public void printHobby(String name){
        System.out.println(this.name + "like" + name);
    }
}
