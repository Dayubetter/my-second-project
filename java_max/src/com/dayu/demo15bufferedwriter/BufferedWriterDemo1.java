package com.dayu.demo15bufferedwriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class BufferedWriterDemo1 {
    public static void main(String[] args) {
        // 缓冲字符输出流 提升了字符输出流的写字符的性质，多了换行功能
        try (
                Writer fw = new FileWriter("java_max/src/com/dayu/demo15bufferedwriter/test.txt");// 覆盖管道
                // 把低级的字符输出流包装成高级的缓冲字符输出流
                BufferedWriter bw = new BufferedWriter(fw);
        ) {
            // 2.写一个字符出去
            bw.write('a');
            bw.write(98);
            bw.write('\r');
            bw.write('\n');
            bw.newLine();
            bw.write('你');
            // 3.写一个字符串出去
            bw.write("hello world");
            bw.newLine();
            // 4.写一个字符数组出去
            char[] chars = {'a', 'b', 'c', 'd', 'e'};
            bw.write(chars);

            bw.flush(); // 刷新缓冲区，将数据写入目的地
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
