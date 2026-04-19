package com.dayu.demo29tcpbs;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class TCPServerMoreDemo2 {
    public static void main(String[] args) throws  Exception{
        // bs架构服务端多客户端多线程接收
        // 1. 创建一个服务端对象，注册端口
        ServerSocket ss = new ServerSocket(8080);

        // 扩展：创建线程池
        ExecutorService pool = new ThreadPoolExecutor(3,10 ,10, TimeUnit.SECONDS
        , new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        while (true) {
            // 2.调用accept方法，获取一个Socket管道，一旦有客户端连接，就会返回一个Socket对象
            Socket socket = ss.accept();
            System.out.println("一个客户端连接了..."+ socket.getInetAddress().getHostAddress());
            // 把这个客户端管道包装成一个任务给线程池处理
            pool.execute(new ServerReader(socket));
        }

    }
}
