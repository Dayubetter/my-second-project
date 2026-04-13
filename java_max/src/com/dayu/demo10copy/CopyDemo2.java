package com.dayu.demo10copy;

import java.io.*;

public class CopyDemo2 {
    public static void main(String[] args) {
        // 使用字节流完成文件的复制操作
        // 源文件:test1.txt
        // 目标文件:test2.txt
            copyFile("java_max/src/com/dayu/demo10copy/test1.txt", "java_max/src/com/dayu/demo10copy/test2.txt");
    }
    // 复制文件
    public static void copyFile(String srcPath, String destPath) {

        // 1.创建一个文件输入流管道与源文件接通
        try( // 只能放资源对象，最终都会调用close()方法 try-with- resources
             InputStream is = new FileInputStream(srcPath);
             OutputStream os = new FileOutputStream(destPath);
             ) {
            // 2.读取一个字节数组，写入一个字节数组
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
