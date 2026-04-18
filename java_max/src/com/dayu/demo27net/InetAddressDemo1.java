package com.dayu.demo27net;

import java.net.InetAddress;

public class InetAddressDemo1 {
    public static void main(String[] args) {
        // 认识InetAddress获取本机IP对象和对方IP对象
        try {
            // 1. 获取本机IP对象
            InetAddress ip1 = InetAddress.getLocalHost();
            System.out.println(ip1);
            System.out.println(ip1.getHostName());
            System.out.println(ip1.getHostAddress());

            // 2. 获取对方IP对象
            InetAddress ip2 = InetAddress.getByName("www.baidu.com");
            System.out.println(ip2);
            System.out.println(ip2.getHostName());
            System.out.println(ip2.getHostAddress());

            // 3. 判断本机与对方主机是否互通
            boolean reachable = ip2.isReachable(5000);
            System.out.println(reachable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
