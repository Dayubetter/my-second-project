package com.dayu.demo16iotest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BufferTest {
    public static void main(String[] args) {
        // 完成demo

        try (
                // 1，创建一个字符缓冲输入流对象与源文件链接
                BufferedReader br = new BufferedReader(new FileReader("java_max/src/com/dayu/demo16iotest/test.txt"));
                // 6. 创建一个字符缓冲输出流对象与目标文件链接
                BufferedWriter bw = new BufferedWriter(new FileWriter("java_max/src/com/dayu/demo16iotest/test2.txt"))
        ) {
            // 2.提前准备一个List集合存储每行内容
            List<String> data = new ArrayList<>();
            // 3.按照行读取数据，存入到data集合中
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
            // 4.给集合中的每一行按照首字母顺序排序
            Collections.sort(data , Comparator.comparingInt(o -> o.charAt(0)));
            System.out.println( data);
            // 5.遍历集合，将每行内容写入到目标文件中
            for (String lineData : data) {
                bw.write(lineData);
                bw.newLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
