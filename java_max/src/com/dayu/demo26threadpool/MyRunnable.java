package com.dayu.demo26threadpool;

// 1.定义一个线程任务实现Runnable接口
public class MyRunnable implements Runnable {
    // 2.重写run方法，设置线程任务
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() +"child thread: " + i);
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
