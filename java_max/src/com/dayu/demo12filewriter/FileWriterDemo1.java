package com.dayu.demo12filewriter;

import java.io.FileWriter;
import java.io.Writer;

public class FileWriterDemo1 {
    public static void main(String[] args) {
        // 文件字符输出流
        // 1.创建一个字符输出流对象，指定写出的目的地
        try (
                Writer fw = new FileWriter("java_max/src/com/dayu/demo12filewriter/test.txt") // 覆盖管道
        ) {
            // 2.写一个字符出去
            fw.write('a');
            fw.write(98);
            fw.write('\r');
            fw.write('\n');
            fw.write('你');
            // 3.写一个字符串出去
            fw.write("hello world");
            // 4.写一个字符数组出去
            char[] chars = {'a', 'b', 'c', 'd', 'e'};
            fw.write(chars);

            fw.flush(); // 刷新缓冲区，将数据写入目的地
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
