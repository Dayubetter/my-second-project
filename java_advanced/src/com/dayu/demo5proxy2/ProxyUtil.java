package com.dayu.demo5proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {
    public static <T> T createProxy(T userService) {
        T proxy = (T) Proxy.newProxyInstance(ProxyUtil.class.getClassLoader(),
                userService.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 用来声明代理对象要干件事情
                        long start = System.currentTimeMillis();
                        // 真正调用业务对象被代理的方法
                        Object result = method.invoke(userService, args);

                        long end = System.currentTimeMillis();
                        System.out.println(method.getName() + "方法耗时：" + (end - start) + "毫秒");
                        return result;
                    }
                });
        return proxy;
    }
}
