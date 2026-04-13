package com.dayu.demo11filereader;

import java.io.FileReader;
import java.io.Reader;

public class FileReaderDemo1 {
    public static void main(String[] args) {
        // 文件字符输入流读取文件内容到程序中
        // 1.创建文件字符输入流管道与源文件接通
        try (
                Reader fr = new FileReader("java_max/src/com/dayu/demo11filereader/test.txt")
        ) {
            // 2. 定义一个字符数组，每次读多个字符
            char[] chars = new char[3];
            int len;
            while ((len = fr.read(chars)) != -1) {
                // 3. 输出字符数组
                String str = new String(chars, 0, len);
                System.out.print(str);
            }
            // 扩展，每次读取多个字符，性能较好，按照字符读取，不会出现乱码，适合读中文
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
