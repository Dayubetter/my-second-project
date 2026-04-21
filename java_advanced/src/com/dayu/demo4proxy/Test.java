package com.dayu.demo4proxy;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        // 创建代理对象
        // 1。 准备一个明星对象，设计明星类
        Star star = new Star("蔡徐坤");
        // 2.为鸡哥创建代理对象
        StarService proxy = ProxyUtil.createProxy(star);
        proxy.sing("I love you");
        String dance = proxy.dance();
        System.out.println(dance);
    }
}
