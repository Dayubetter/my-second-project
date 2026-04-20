package com.dayu.demo3annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationDemo4 {
    public static void main(String[] args) throws  Exception{
        AnnotationDemo4 demo4 = new AnnotationDemo4();
        // 1,获取类对象
        // 2.获取所有方法
        // 3.遍历方法，判断方法上是否有MyTest注解，如果有，就执行方法
        Class c1 = AnnotationDemo4.class;
        Method[] methods = c1.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyTest.class)) {
                MyTest myTest = method.getDeclaredAnnotation(MyTest.class);
                int count = myTest.count();
                for (int i = 0; i < count; i++) {
                    method.invoke(demo4);
                }
            }
        }
    }
    // 注解的应用场景，模拟junit框架
    @MyTest
    public void test1() {
        System.out.println("test1()");
    }
    @MyTest(count = 1)
    public void test2() {
        System.out.println("test2()");
    }

    public void test3() {
        System.out.println("test3()");
    }
}
