package com.dayu.demo26threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo1 {
    public static void main(String[] args) {
        // 创建线程池对象来使用
        // 1.使用线程池的实现类ThreadPoolExecutor声明七个参数来创建线程池对象
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3,5,
                10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        // 2.使用线程池处理任务，看会不会复用线程
        Runnable task = new MyRunnable();
        pool.execute(task);  // 提交第一个任务 创建一个线程 自动启动线程处理这个任务
        pool.execute(task); // 提交第二个任务
        pool.execute(task); // 提交第三个任务
        pool.execute(task);
        pool.execute(task);
        pool.execute(task);
        pool.execute(task); // 到了临时线程的创建时机
        pool.execute(task); // 到了临时线程的创建时机
        pool.execute(task); // 到了拒绝策略

        // 3.关闭线程池
        pool.shutdown(); // 等待所有任务执行完毕，再关闭线程池
    }
}
