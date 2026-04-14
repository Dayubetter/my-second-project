package com.dayu.demo17inputstreamreader;

import java.io.*;

public class Demo1 {
    public static void main(String[] args) {
        // 不同编码读取乱码问题
        // 代码：UTF-8 文件：UTF-8 读取不乱码
        // 代码：UTF-8 文件：GBK 读取乱码
        // 解决思路：先获取文件的原始字节流，再将其按照真实的字符集编码转换成字符输入流，这样字符输入流中的字符就不乱码了
        try (
                // 1.先提取文件的原始字节流
                InputStream is = new FileInputStream("java_max/src/com/dayu/demo17inputstreamreader/test2.txt");
                // 2.指定字符集把原始字节流转换成字符输入流
                Reader fr = new InputStreamReader(is,"GBK");
                // 把低级的字符流包装成高级的缓冲字符流
                BufferedReader br = new BufferedReader(fr);
        ) {
            // 使用循环读取多行
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
