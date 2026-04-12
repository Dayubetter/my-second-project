package com.dayu.demo8fileinputstream;

import java.io.*;

public class FileInputStreamDemo1 {
    public static void main(String[] args) throws Exception {
        // 1.创建文件字节输入流管道于源文件接通
        // InputStream is = new FileInputStream(new File("java_max/src/com/dayu/demo8fileinputstream/test.txt"));
        InputStream is = new FileInputStream("java_max/src/com/dayu/demo8fileinputstream/test1.txt");

        // 2，开始读取文件中的字节并输出
        // 定义一个变量记住每次读取的一个字节
//        int b;
//        while ((b = is.read()) != -1) {
//            System.out.print((char) b);
//        }
        // 每次读取一个字节的问题，性能较差，读取汉字输出一定会乱码
        // 定义一个字节数组用于每次读取字节
        // byte[] bytes = new byte[1024];  // 1kb
        byte[] bytes = new byte[3];
        // 定义一个变量记住每次读取了多少字节,读取多少输出多少len
        int len;
        while ((len = is.read(bytes)) != -1) {
            String str = new String(bytes, 0, len);
            System.out.println(str);
        }
    }
}
