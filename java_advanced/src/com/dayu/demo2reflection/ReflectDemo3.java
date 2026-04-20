package com.dayu.demo2reflection;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ReflectDemo3 {
    public static void main(String[] args) throws  Exception{
        // 反射的基本作用
        // 类的全部成分获取操作
        // 可以破坏封装性
        // 可以绕过泛型的约束
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("java");
        //list.add(9.9);

        Class c1 = list.getClass(); // 获取ArrayList的Class对象
        Method add = c1.getDeclaredMethod("add", Object.class);
        // 获取add方法执行
        add.invoke(list,9.9);
        System.out.println(list);
    }
}
