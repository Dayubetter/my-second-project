package com.dayu.demo21thread;

public class CreateThread2Runnable {
    public static void main(String[] args) {
        // 实现Runnable接口来创建
        // 3.创建线程任务类的对象代表一个线程任务
        Runnable task = new MyRunnable();
        // 4.把线程任务对象交给一个线程对象来处理
        Thread t1 = new Thread(task); // public Thread(Runnable r)
        // 5.启动线程
        t1.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("main thread: " + i);
        }
    }
}

// 1.定义一个线程任务实现Runnable接口
class MyRunnable implements Runnable {
    // 2.重写run方法，设置线程任务
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("child thread: " + i);
        }
    }
}
