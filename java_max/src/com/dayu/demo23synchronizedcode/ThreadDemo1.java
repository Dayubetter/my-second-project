package com.dayu.demo23synchronizedcode;

public class ThreadDemo1 {
    public static void main(String[] args) {
        // 模拟线程安全问题，线程同步的方式一演示：同步代码块
        // 1. 设计一个账户类，用于创建小明小红的共同账户对象，存入10w元
        Account acc = new Account("1001", 10000);
        // 2. 设计线程类：创建小明和小红两个线程，模拟小明和小红同时去同一个账户取款10w。
        new DrawThread("xm", acc).start();
        new DrawThread("xh", acc).start();

        Account acc2 = new Account("1002", 10000);
        // 2. 设计线程类：创建小明和小红两个线程，模拟小明和小红同时去同一个账户取款10w。
        new DrawThread("xQ", acc2).start();
        new DrawThread("xW", acc2).start();
    }




}
