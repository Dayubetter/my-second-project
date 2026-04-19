package com.dayu.demo28tcp;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClientMoreDeomo1 {
    public static void main(String[] args) throws  Exception{
        // 实现TCP通信的多发多收，客户端
        // 1，常见Socket管道对象，请求与服务端的socket链接，可靠链接
        Socket socket = new Socket("127.0.0.1", 9999);

        // 2.从socket管道中得到一个字节输出流
        OutputStream os = socket.getOutputStream();

        // 3，特殊数据流
        DataOutputStream dos = new DataOutputStream(os);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要发送的数据：");
            String msg = scanner.nextLine();
            if ("886".equals(msg)) {
                System.out.println("程序结束");
                socket.close();
                break;
            }
            dos.writeUTF(msg); // 发送数据
            dos.flush(); // 刷新缓冲区
        }
    }
}
