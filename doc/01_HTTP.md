1. Hyper Text Transfer Protocol (HTTP) is the protocol that powers the web.
2. It is the foundation of all web communication.
3. HTTP is a request-response protocol.
4. A request is a message sent from a client to a server.
5. A response is a message sent from a server to a client.
6. HTTP is stateless.
7. HTTP is a protocol.
### 超文本传输协议
- 特点：
1. 基于TCP协议：面向连接，安全
2. 基于请求-响应模型的：一次请求对应一次响应
3. HTTP协议是无状态的协议，对于事务处理没有记忆能力。每次请求-响应都是独立的
    - 缺点：多次请求间不能共享数据
    - 优点：速度快

#### 请求数据格式
>Host ： 请求的主机名
>
>User-Agent ： 浏览器版本，例如Chrome浏览器的标识类似Mozilla/5.0 ... Chrome/79，IE浏览器的标识类似Mozilla/5.0(Windows NT ...) like Gecko
> 
>Accept ： 浏览器可处理的内容类型，例如text/html，text/plain，image/gif，image/jpeg，* / *
> 
>Accept-Language ： 浏览器可处理的语言类型，例如zh-cn，en-us，ja
> 
>Accept-Encoding ： 浏览器可支持的压缩类型，例如gzip，deflate
> 
>content-type ： 请求发送的数据类型
> 
>Content-Length ： 请求数据长度
#### 请求数据获取
- Web服务器(Tomcat)对HTTP协议的请求数据进行解析，并进行了封装(HttpServletRequest),在调用Controller方法的时候传递给了该方法。
这样，就使得程序员不必直接对协议进行操作，让Web开发更加便捷

```java
@RequestMapping("/request")
public String request(HttpServletRequest request) {
    // 1. 获取请求参数name，age
    String name = request.getParameter("name"); // Tom
    // 2，获取请求路径uri和url
    String uri = request.getRequestURI(); // /request
    String url = request.getRequestURL().toString(); // http://localhost:8080/request
    // 3. 获取请求头 user-agent
    String userAgent = request.getHeader("user-agent"); // Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36
    // 4. 获取请求方式
    String method = request.getMethod(); // GET
    // 5. 获取请求的查询字符串
    String queryString = request.getQueryString(); // name=Tom&age=18
    return "request success";
}
```
#### 响应数据格式
- 响应码

1xx ：信息，表示临时响应，如100表示正在处理中
2xx ：成功，表示成功处理了请求，如200表示成功
3xx ：重定向，表示重定向，如301表示永久重定向，302表示临时重定向
4xx ：客户端错误，表示客户端错误，如404表示找不到页面，405表示请求方式错误
5xx ：服务器错误，表示服务器错误，如500表示服务器内部错误

状态码	英文描述	解释
200	OK	客户端请求成功，即处理成功，这是我们最想看到的状态码
302	Found	指示所请求的资源已移动到由Location响应头给定的 URL，浏览器会自动重新访问到这个页面
304	Not Modified	告诉客户端，你请求的资源至上次取得后，服务端并未更改，你直接用你本地缓存吧。隐式重定向
400	Bad Request	客户端请求有语法错误，不能被服务器所理解
403	Forbidden	服务器收到请求，但是拒绝提供服务，比如：没有权限访问相关资源
404	Not Found	请求资源不存在，一般是URL输入有误，或者网站资源被删除了
405	Method Not Allowed	请求方式有误，比如应该用GET请求方式的资源，用了POST
428	Precondition Required	服务器要求有条件的请求，告诉客户端要想访问该资源，必须携带特定的请求头
429	Too Many Requests	指示用户在给定时间内发送了太多请求（“限速”），配合 Retry-After(多长时间后可以请求)响应头一起使用
431	Request Header Fields Too Large	请求头太大，服务器不愿意处理请求，因为它的头部字段太大。请求可以在减少请求头域的大小后重新提交。
500	Internal Server Error	服务器发生不可预期的错误。服务器出异常了，赶紧看日志去吧
503	Service Unavailable	服务器尚未准备好处理请求，服务器刚刚启动，还未初始化好

- 响应头

Content-type ： 表示响应内容的类型
Content-Length ： 响应内容长度
Content-Disposition ： 表示响应内容 disposition
Content-Encoding ： 响应压缩算法，如gzip
Content-Language ： 响应内容语言
Set-Cookie ： 告诉浏览器为当前页面所在的域设置cookie

#### 响应数据设置
- Web服务器(Tomcat)对HTTP协议的响应数据进行封装(HttpServletResponse),在调用Controller方法返回数据时，
会自动将数据封装成HTTP协议响应数据，并返回给浏览器。 这样，就使得程序员不必直接对协议进行操作，让Web开发更加便捷

```java
@RequestMapping("/response")
public void response(HttpServletResponse response) throws IOException {
    // 1. 设置响应码
    response.setStatus(401);
    // 2. 添加响应头
    response.addHeader("name", "Tom");
    // 3. 设置响应体
    response.getWriter().write("<h1> response success </h1>");
}
```

```java
@RequestMapping("/response")
public ResponseEntity< String> response() {
    return ResponseEntity.status(401) // 1. 设置响应码
            .header("name", "Tom") // 2. 添加响应头
            .body("<h1> response success </h1>"); // 3. 添加响应体
}
```