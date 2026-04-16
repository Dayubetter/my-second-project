package com.dayu.demo25lock;

public class ThreadDemo1 {
    public static void main(String[] args) {
        // Lock
        // 1. 设计一个账户类，用于创建小明小红的共同账户对象，存入10w元
        Account acc = new Account("1001", 10000);
        // 2. 设计线程类：创建小明和小红两个线程，模拟小明和小红同时去同一个账户取款10w。
        new DrawThread("xm", acc).start();
        new DrawThread("xh", acc).start();
    }




}
