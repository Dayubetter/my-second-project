package com.dayu.demo21thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CreateThread3Callable {
    public static void main(String[] args) {
        // 使用实现Callable接口 ，可以获取线程任务执行完毕后的结果

        // 3. 创建Callable接口的实现类对象
        Callable<String> c1 = new MyCallable(100);
        // 4. 把Callable对象封装成一个真正的线程任务对象FutureTask对象
        /**
         * 未来任务对象的作用？
         *   a. 本质是一个Runnable线程任务对象，可以交给Thread线程对象处理
         *   b. 获取线程任务执行结果
         */
        FutureTask<String> ft1 = new FutureTask<>(c1); // public FutureTask(Callable<V> callable)
        // 5，把线程任务对象交给一个线程对象来处理
        Thread t1 = new Thread(ft1);
        t1.start();

        Callable<String> c2 = new MyCallable(50);
        FutureTask<String> ft2 = new FutureTask<>(c2); // public FutureTask(Callable<V> callable)
        // 5，把线程任务对象交给一个线程对象来处理
        Thread t2 = new Thread(ft2);
        t2.start();

        // 取结果
        try {
            // 如果发现第一个线程还没有执行完毕，会让出cpu，等第一个线程执行完毕，才会往下执行
            System.out.println(ft1.get());
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            System.out.println(ft2.get());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

// 1.定义一个实现类实现Callable接口
class MyCallable implements Callable<String> {
    private int n;
    public MyCallable(int n) {
        this.n = n;
    }
    // 2.实现call方法，设置线程任务
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
        return "child thread " + n + "sum is: " + sum;
    }
}
