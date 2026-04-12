package com.dayu.demo7fileio;

import java.io.File;
import java.io.IOException;

public class FileDemo1 {
    public static void main(String[] args) throws IOException {
        // 创建File对象代表文件(文件/目录)，搞清楚其提供的对文件进行操作的方法
        // 1. 创建File对象，去获取某个文件的信息
        File file = new File("java_max/src/com/dayu/demo7fileio/test.txt");
        System.out.println(file.getName());
        System.out.println(file.length());
        // 创建对象代表不存在的文件路径
        File file1 = new File("java_max/src/com/dayu/demo7fileio/test1.txt");
        System.out.println(file1.exists());
        // System.out.println(file1.createNewFile()); // 创建文件
        // 创建对象代表不存在的文件夹路径
        // File file2 = new File("java_max/src/com/dayu/demo7fileio/test1/test2");
        // System.out.println(file2.mkdir()); // 创建一级文件夹
        // System.out.println(file2.mkdirs());
        // System.out.println(file2.delete()); // 删除文件夹
    }
}
