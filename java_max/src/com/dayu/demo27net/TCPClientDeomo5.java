package com.dayu.demo27net;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClientDeomo5 {
    public static void main(String[] args) throws  Exception{
        // 实现TCP通信的一发一收，客户端
        // 1，常见Socket管道对象，请求与服务端的socket链接，可靠链接
        Socket socket = new Socket("127.0.0.1", 9999);

        // 2.从socket管道中得到一个字节输出流
        OutputStream os = socket.getOutputStream();

        // 3，特殊数据流
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeInt(1);
        dos.writeUTF("hello,server");

        // 4关闭输入流
        socket.close();
    }
}
