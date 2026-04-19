### 网络编程
数据通信的方法
让设备中的程序与网络上其他设备中的程序进行数据交互的技术(实现网络的数据通信)

InetAddress 代表IP地址
常用方法

| InetAddress类的常用方法                                | 说明                          |
|:-------------------------------------------------|:----------------------------|
| public static InetAddress getLocalHost()         | 获取本机IP，返回一个InetAddress对象    |
| Public String getHostName()                      | 返回该ip地址对象对应的主机名             |
| Public String getHostAddress()                   | 获取该ip地址对象中的ip地址信息           |
| Public static InetAddress getByName(String host) | 根据ip地址或域名，返回一个inetAddress对象 |
| public Boolean isReachable(int timeout)          |判断主机在指定毫秒内与该ip对应的主机是否能连通|

#### 端口
用来标记正在计算机设备上运行的应用程序，被规定为一个16位的二进制，范围是0~65535   2^16
端口分类：
- 周知端口：0~1023，被预先定义的知名应用占用(比如HTTP占用80端口，FTP占用21端口)
- 注册端口：1024~49151，分配给用户进程或某些应用程序
- 动态端口：49152~65535，之所以成为动态端口，是因为它一般不固定分配某种进程，而是动态分配
注意：自己开发用注册端口，且一个设备中不能出现两个程序的端口号一样，否则报错

#### 通信协议
网络上通信的设备，事先规定的连接规则，以及传输数据的规则被成为网络通信协议
开放式网络互联标准:OSI网络参考模型
OSI网络参考模型：全球网络互联标准。
TCP/TP网络模型：事实上的国际标准。

| OSI网络参考模型 | TCP/IP网络模型 | 各层对应             | 面向操作                         |
|:----------|:-----------|------------------|:-----------------------------|
| 应用层       | 应用层        | HTTP、FTP、SMTP... | 应用程序需要关注的：浏览器，邮箱。程序员一般在这一层开发 |
| 表示层       | ~          | ~                | ~                            |
| 会话层       | ~          | ~                | ~                            |
| 传输层       | 传输层        | TCP/UDP...       | 选择使用的TCP、UDP协议               |
| 网络层       | 网络层        | IP...            | 封装源和目标IP                     |
| 数据链路层     | 数据链路层+物理层  | 比特流...           | 物理设备中传输                      |
| 物理层       | ~          | ~                | ~                            |

#### UDP通信的实现
DatagramSocket：用于创建客户端、服务端

|构造器| 说明                            |
|:----|:------------------------------|
|public DatagramSocket() throws SocketException| 创建一个客户端的socket对象，系统对随机分配一个端口号 |
|public DatagramSocket(int port) throws SocketException| 创建一个服务端的socket对象，并指定端口号       |

|方法| 说明        |
|:----|:----------|
|public void send(DatagramPacket p) throws IOException| 发送数据包     |
|public void receive(DatagramPacket p) throws IOException| 使用数据包接收数据 |

DatagramPacket：创建数据包

|构造器| 说明           |
|:----|:-------------|
|public DatagramPacket(byte[] buf, int length, InetAddress ip, int port)| 创建发出去的数据包对象  |
|public DatagramPacket(byte[] buf, int length)| 创建用来接收的数据包对象 |

#### TCP通信的实现
客户端

|构造器| 说明                                                         |
|:----|:-----------------------------------------------------------|
|public Socket(String host, int port) throws IOException|根据指定的服务器ip，端口号请求与服务端建立连接，连接通过，就获得了客户端socket|

|方法| 说明        |
|:----|:----------|
|public OutputStream getOutputStream() throws IOException| 获取字节输出流对象 |
|public InputStream getInputStream() throws IOException| 获取字节输入流对象 |

- 服务端是java.net包下的ServerSocket类

|构造器| 说明                                                         |
|:----|:-----------------------------------------------------------|
|public ServerSocket(int port) throws IOException| 创建一个服务端的socket对象，并指定端口号 |

|方法| 说明                                          |
|:----|:--------------------------------------------|
|public Socket accept() throws IOException| 阻塞等待客户端的连接请求，一旦与某个客户端成功连接，则返回服务端这边的Socket对象 |