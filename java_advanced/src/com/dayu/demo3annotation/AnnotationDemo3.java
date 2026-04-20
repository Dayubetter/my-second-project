package com.dayu.demo3annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class AnnotationDemo3 {
    // 注解解析
    @Test
    public void parseClass() throws  Exception{
        // 1. 获取Class对象
        Class c1 = Class.forName("com.dayu.demo3annotation.Demo");
        // 2. 判断这个类上是否陈列了注解@MyTest2
        if (c1.isAnnotationPresent(MyTest2.class)) {
            // 3. 获取注解对象
            MyTest2 myTest2 = (MyTest2) c1.getDeclaredAnnotation(MyTest2.class);
            // 4. 获取注解属性值
            String[] address = myTest2.address();
            double price = myTest2.price();
            String value = myTest2.value();
            // 5. 输出注解属性值
            System.out.println(Arrays.toString( address));
            System.out.println(price);
            System.out.println(value);
        }
    }
    @Test
    public void parseMethod() throws  Exception{
        Class c1 = Class.forName("com.dayu.demo3annotation.Demo");
        Method methods = c1.getDeclaredMethod("show");
        if (methods.isAnnotationPresent(MyTest2.class)) {
            MyTest2 myTest2 = methods.getDeclaredAnnotation(MyTest2.class);
            String[] address = myTest2.address();
            double price = myTest2.price();
            String value = myTest2.value();
            System.out.println(Arrays.toString( address));
            System.out.println(price);
            System.out.println(value);
        }
    }
}
