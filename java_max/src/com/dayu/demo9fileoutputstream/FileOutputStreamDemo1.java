package com.dayu.demo9fileoutputstream;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class FileOutputStreamDemo1 {
    public static void main(String[] args) throws  Exception{
        // 学会使用文件字节输出流
        // 1.创建文件字节输出流管道与目标文件接通
        // OutputStream os = new FileOutputStream("java_max/src/com/dayu/demo9fileoutputstream/test_out.txt"); // 覆盖管道
        OutputStream os = new FileOutputStream("java_max/src/com/dayu/demo9fileoutputstream/test_out.txt" , true); // 追加数据

        // 2.写数据
        os.write(97); // 写一个字节数据
        os.write("\r\n".getBytes()); // 换行
        os.write('b'); // 写一个字符数据

        // 3.写一个字节数组出去
        byte[] bytes = {97, 98, 99, 100, 101};
        byte[] bytes2 = "测试666".getBytes(); // 编码成字节数组然后再写出去
        os.write(bytes);
        os.write("\r\n".getBytes()); // 换行
        os.write(bytes2);
        os.write("\r\n".getBytes()); // 换行
        os.write(bytes2, 0, 3); // 0:起始索引, 3:长度
        os.write("\r\n".getBytes()); // 换行
        os.close();
    }
}
