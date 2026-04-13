package com.dayu.demo10copy;

import java.io.*;

public class CopyDemo1 {
    public static void main(String[] args) throws IOException {
        // 使用字节流完成文件的复制操作
        // 源文件:test1.txt
        // 目标文件:test2.txt
            copyFile("java_max/src/com/dayu/demo10copy/test1.txt", "java_max/src/com/dayu/demo10copy/test2.txt");
            InputStream is1 = new FileInputStream("java_max/src/com/dayu/demo10copy/test1.txt");
            InputStream is2 = new FileInputStream("java_max/src/com/dayu/demo10copy/test2.txt");
            System.out.println(is1.read());  // 返回的第一个字节 "你" 228
            System.out.println(is2.read());
            File file1 = new File("java_max/src/com/dayu/demo10copy/test1.txt");
            System.out.println("文件大小:" + file1.length() + "字节");
            File file2 = new File("java_max/src/com/dayu/demo10copy/test2.txt");
            System.out.println("文件大小:" + file2.length() + "字节");
    }
    // 复制文件
    public static void copyFile(String srcPath, String destPath) {
        InputStream is = null;
        OutputStream os = null;
        try {
            // 1.创建一个文件输入流管道与源文件接通
             is = new FileInputStream(srcPath);
             os = new FileOutputStream(destPath);
            // 2.读取一个字节数组，写入一个字节数组
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
