package com.dayu.demo27net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientDemo1 {
    public static void main(String[] args) throws  Exception{
        System.out.println("客户端启动了...");
        // 完成udp通信的一发一收，客户端
        // 1. 创建发送端对象
        DatagramSocket socket = new DatagramSocket();
        // 2. 创建数据包对象封装要发送的数据
        byte[] bytes = "I'm Client,hello".getBytes();
        /**
         * 参数一：发送的数据
         * 参数二：发送的字节长度
         * 参数三：指定发送的目标ip
         * 参数四：指定发送的目标端口
         */
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8080);

        // 3. 让发送端对象发送数据包的数据
        socket.send(packet);
        socket.close();
    }
}
