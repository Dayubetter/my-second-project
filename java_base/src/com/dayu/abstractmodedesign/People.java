package com.dayu.abstractmodedesign;

public abstract class People {
    // 模板方法设计模式
    public void write() {
        System.out.println("111");
        writeMid();
        System.out.println("333");
    }

    public abstract void writeMid();
}
