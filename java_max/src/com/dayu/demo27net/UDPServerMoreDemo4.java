package com.dayu.demo27net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerMoreDemo4 {
    public static void main(String[] args) throws  Exception{
        System.out.println("服务端启动了...");
        // 创建一个UDP服务端开发
        // 1. 创建一个UDP服务端对象，注册端口
        DatagramSocket socket = new DatagramSocket(8080);

        // 2.创建一个数据包对象负责接收数据。
        byte[] bytes = new byte[1024 * 64];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);

        while (true) {
            // 3. 接收数据，将数据封装到数据包对象的字节数组中去
            socket.receive(packet); // 阻塞方法

            // 4.看看数据是否收到了
            // 获取数据的长度
            int length = packet.getLength();
            String data = new String(bytes);
            System.out.println(data);

            // 获取对方的ip对象和程序端口
            String ip = packet.getAddress().getHostAddress();
            int port = packet.getPort();
            System.out.println(ip + ":" + port);
            System.out.println("------------------");
        }
    }
}
