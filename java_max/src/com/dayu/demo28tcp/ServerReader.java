package com.dayu.demo28tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerReader extends  Thread{
    private Socket socket;

    public ServerReader(Socket  socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 读取管道的消息
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
