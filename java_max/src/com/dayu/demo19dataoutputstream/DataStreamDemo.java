package com.dayu.demo19dataoutputstream;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class DataStreamDemo {
    public static void main(String[] args) {
        // 特殊数据流的使用
        // 怎么写就要怎么收要对应起来
        try(
                DataOutputStream dos = new DataOutputStream(new FileOutputStream("java_max/src/com/dayu/demo19dataoutputstream/test.txt"));
                ){
            dos.writeByte(10);
            dos.writeInt(100);
            dos.writeBoolean(true);
            dos.writeChar('a');
            dos.writeDouble(3.14);
            dos.writeUTF("hello world");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
