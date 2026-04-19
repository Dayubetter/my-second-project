package com.dayu.demo28tcp;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerMoreDemo2 {
    public static void main(String[] args) throws  Exception{
        // 服务端TCP一发一收 接收多客户端
        // 1. 创建一个服务端对象，注册端口
        ServerSocket ss = new ServerSocket(9999);

        while (true) {
            // 2.调用accept方法，获取一个Socket管道，一旦有客户端连接，就会返回一个Socket对象
            Socket socket = ss.accept();
            System.out.println("一个客户端连接了..."+ socket.getInetAddress().getHostAddress());
            // 3.把这客户端管道交给一个独立的子线程专门负责接收这个管道的消息
            new ServerReader(socket).start();
        }

    }
}
