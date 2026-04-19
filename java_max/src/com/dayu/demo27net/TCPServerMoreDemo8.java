package com.dayu.demo27net;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerMoreDemo8 {
    public static void main(String[] args) throws  Exception{
        // 服务端TCP一发一收
        // 1. 创建一个服务端对象，注册端口
        ServerSocket ss = new ServerSocket(9999);
        // 2.调用accept方法，获取一个Socket管道，一旦有客户端连接，就会返回一个Socket对象
        Socket socket = ss.accept();
        // 3，获取输入流，读取客户端发送的数据
        InputStream is = socket.getInputStream();
        // 4，把字节输入流转换成特殊输入流
        DataInputStream dis = new DataInputStream(is);
        while (true) {
            // 5，读取数据
            String msg = dis.readUTF(); // 等待读取客户端发送的数据
            System.out.println("msg:" + msg);

            // 客户端的ip 和程序端口
            System.out.println(socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
        }

    }
}
