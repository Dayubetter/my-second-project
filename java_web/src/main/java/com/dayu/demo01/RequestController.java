package com.dayu.demo01;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    @RequestMapping("/request")
    public String request(HttpServletRequest  request){
        // 1. 获取请求方式
        String method = request.getMethod(); //  GET
        System.out.println("请求方式:" + method);
        // 2. 获取请求url
        String url = request.getRequestURL().toString(); // http://localhost:8080/request
        System.out.println("请求url:" + url);
        String requestURI = request.getRequestURI(); // /request
        System.out.println("请求uri:" + requestURI);
        // 3. 获取请求协议
        String protocol = request.getProtocol(); // HTTP/1.1
        System.out.println("请求协议:" + protocol);
        // 4. 获取请求参数 = name
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("请求参数:" + name + ":" + age);
        // 5， 获取请求头 -Accept
        String accept = request.getHeader("Accept");
        System.out.println("请求头:" + accept);
        return "OK！";
    }
}
