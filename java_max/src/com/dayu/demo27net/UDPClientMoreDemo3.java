package com.dayu.demo27net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClientMoreDemo3 {
    public static void main(String[] args) throws  Exception{
        System.out.println("客户端启动了...");
        // 完成udp通信的多发多收，客户端
        // 1. 创建发送端对象
        DatagramSocket socket = new DatagramSocket();

        Scanner sc = new Scanner(System.in);
        while (true) {
            // 2. 创建数据包对象封装要发送的数据
            System.out.println("请输入要发送的数据：");
            String msg = sc.nextLine();
            byte[] bytes = msg.getBytes();
            if ("886".equals(msg)) {
                System.out.println("程序结束");
                socket.close();
                break;
            }
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length,
                    InetAddress.getLocalHost(), 8080);

            // 3. 让发送端对象发送数据包的数据
            socket.send(packet);
        }
    }
}
