package com.dayu.extendsoverride;

public class Test {
    public static void main(String[] args) {
        // 当子类觉得某个方法不好用，或者无法满足自己的需求时，子类可以重写一个方法名
        // 参数列表一样的方法，去覆盖父类这个方法
    }
}

class Cat extends Animal{
    // 方法重写：方法名，形参列表必须一样
    @Override
    public void cry(){
        System.out.println("cat cry");
    }
}
class Animal {
    public void cry() {
        System.out.println("animal cry");
    }
}