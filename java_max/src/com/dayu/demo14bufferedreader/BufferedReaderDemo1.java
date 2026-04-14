package com.dayu.demo14bufferedreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class BufferedReaderDemo1 {
    public static void main(String[] args) {
        // 文件字符输入流读取文件内容到程序中
        // 1.创建文件字符输入流管道与源文件接通
        try (
                Reader fr = new FileReader("java_max/src/com/dayu/demo14bufferedreader/test.txt");
                // 把低级的字符流包装成高级的缓冲字符流
                BufferedReader br = new BufferedReader(fr);
        ) {
//            // 2. 定义一个字符数组，每次读多个字符
//            char[] chars = new char[3];
//            int len;
//            while ((len = fr.read(chars)) != -1) {
//                // 3. 输出字符数组
//                String str = new String(chars, 0, len);
//                System.out.print(str);
//            }
            //System.out.println(br.readLine());
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
