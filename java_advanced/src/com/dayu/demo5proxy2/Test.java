package com.dayu.demo5proxy2;

import java.util.Arrays;

/**
 * 使用动态代理解决实际问题，使用代理的好处
 */
public class Test {
    public static void main(String[] args) throws  Exception{
        // 1.创建用户业务对象。
        UserService userService = ProxyUtil.createProxy(new UserServiceImpl());

        // 2.调用用户业务的功能
        userService.login("admin", "123");

        userService.deleteUser();

        String[] names = userService.selectUsers();
        System.out.println("查询到的用户是：" + Arrays.toString(names));

    }
}
