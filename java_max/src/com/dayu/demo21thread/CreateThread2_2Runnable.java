package com.dayu.demo21thread;

public class CreateThread2_2Runnable {
    public static void main(String[] args) {
        // 使用实现Runnable接口的匿名内部类来创建
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("child thread: " + i);
                }
            }
        };

        Thread t1 = new Thread(task); // public Thread(Runnable r)
        t1.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("child thread3: " + i);
                }
            }
        }).start();

        new Thread(()->{
                for (int i = 0; i < 5; i++) {
                    System.out.println("child thread2: " + i);
                }
        }).start();


        for (int i = 0; i < 5; i++) {
            System.out.println("main thread: " + i);
        }
    }
}
