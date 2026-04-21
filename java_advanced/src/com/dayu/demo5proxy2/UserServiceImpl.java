package com.dayu.demo5proxy2;

/**
 * 用户业务实现类 面向接口编程
 */
public class UserServiceImpl implements UserService{
    @Override
    public void login(String username, String password) throws Exception {
        //long start = System.currentTimeMillis();

        if ("admin".equals(username) && "123".equals(password)) {
            System.out.println("登录成功");
        } else {
            throw new Exception("用户名或密码错误");
        }
        Thread.sleep(1000);
        //long end = System.currentTimeMillis();
        //System.out.println("登录方法耗时：" + (end - start) + "毫秒");
    }

    @Override
    public void deleteUser() throws Exception {
        //long start = System.currentTimeMillis();
        System.out.println("删除用户成功");
        Thread.sleep(1000);
        //long end = System.currentTimeMillis();
        //System.out.println("删除用户方法耗时：" + (end - start) + "毫秒");
    }

    @Override
    public String[] selectUsers() throws Exception {
        //long start = System.currentTimeMillis();

        System.out.println("查询用户成功");
        String[] names = {"小王", "小李", "小张"};
        Thread.sleep(500);

        //long end = System.currentTimeMillis();
        //System.out.println("查询用户方法耗时：" + (end - start) + "毫秒");
        return names;
    }
}
