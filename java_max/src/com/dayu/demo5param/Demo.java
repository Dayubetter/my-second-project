package com.dayu.demo5param;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        // 可变参数
        sum(); // 不传参数
        sum(1); // 传一个参数
        sum(1,2,3,4,5,6,7,8,9,10); // 传多个参数
        sum(new int[]{1,2,3,4,5,6,7,8,9,10}); // 传一个数组参数
    }

    // 可变参数在形参里面只有一个，可变参数放在形参列表的最后一个参数
    public static void sum(int... nums){
        // 内部怎么拿数据
        // 可变参数对内实际上就是一个数组，nums就是数组
        System.out.println(nums.length);
        System.out.println(Arrays.toString(nums));
        System.out.println("------------------------------------------");
    }
}
