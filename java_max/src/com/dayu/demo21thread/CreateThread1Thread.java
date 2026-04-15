package com.dayu.demo21thread;

public class CreateThread1Thread {
    // main方法本身是有一条主线程负责推荐执行的
    public static void main(String[] args) {
        // 多线程的创建方式1，继承thread类来实现
        // 4. 创建线程类的对象
        Thread t1 = new MyThread();
        // 5.调用start方法，启动线程
        t1.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("main thread: " + i);
        }
    }
}
// 1. 定义一个子类继承thread类，成为一个线程类
class MyThread extends Thread {
    public MyThread(String  name) {
        super(name);
    }

    public MyThread() {
        super();
    }

    // 2. 重写thread类的run方法

    @Override
    public void run() {
        // 3. 在run方法中编写线程的任务代码，线程要干的活
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "child thread: " + i);
        }
    }
}
