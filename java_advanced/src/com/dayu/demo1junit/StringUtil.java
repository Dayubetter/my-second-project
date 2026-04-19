package com.dayu.demo1junit;

public class StringUtil {
    public static void printNumber(String name){
        if(name == null){
            System.out.println("名字不能为空");
            return;
        }
        System.out.println("名字长度是：" + name.length());
    }

    /**
     * 求字符串的最大索引
     * @param data
     * @return
     */
    public static int getMaxIndex(String data){
        if(data == null || "".equals( data)){
            return -1;
        }
        return data.length() - 1;
        // return data.length();
    }
}
