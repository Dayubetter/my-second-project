package com.dayu.demo5proxy2;

/**
 * 用户业务接口
 */
public interface UserService {
    // 登录功能
    void login(String username, String password) throws  Exception;
    // 删除用户
    void deleteUser() throws Exception;
    // 查询用户，返回数组的形式
    String[] selectUsers() throws Exception;
}
