package com.dayu.demo26threadpool;

import java.util.concurrent.Callable;

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
        return Thread.currentThread().getName() + "child thread " + n + "sum is: " + sum;
    }
}
