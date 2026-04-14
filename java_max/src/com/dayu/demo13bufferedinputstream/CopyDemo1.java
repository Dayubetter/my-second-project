package com.dayu.demo13bufferedinputstream;

import java.io.*;

public class CopyDemo1 {
    public static void main(String[] args) {
        // 缓冲字节流的使用
        // 源文件:test1.txt
        // 目标文件:test2.txt
            copyFile("java_max/src/com/dayu/demo13bufferedinputstream/test1.txt",
                    "java_max/src/com/dayu/demo13bufferedinputstream/test2.txt");
    }
    // 复制文件
    public static void copyFile(String srcPath, String destPath) {

        // 1.创建一个文件输入流管道与源文件接通
        try( // 只能放资源对象，最终都会调用close()方法 try-with- resources
             InputStream is = new FileInputStream(srcPath);
             // 把低级的字节流包装成高级的缓冲字节流
             InputStream bis = new BufferedInputStream(is);
             OutputStream os = new FileOutputStream(destPath);
             // 把低级的字节流包装成高级的缓冲字节流
              OutputStream bos = new BufferedOutputStream(os);
             ) {
            // 2.读取一个字节数组，写入一个字节数组
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
