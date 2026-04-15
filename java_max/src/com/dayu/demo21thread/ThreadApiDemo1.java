package com.dayu.demo21thread;

public class ThreadApiDemo1 {
    public static void main(String[] args) {
        Thread t1 = new MyThread("t1");
        // t1.setName("t1");
        t1.start();
        System.out.println(t1.getName());

        Thread t2 = new MyThread();
        t2.setName("t2");
        t2.start();
        System.out.println(t2.getName());

        // 哪个线程运行，哪个线程就是当前线程
        Thread m = Thread.currentThread(); // 获取当前正在执行的线程对象 主线程
        m.setName("main");
        System.out.println(m.getName());
    }
}
