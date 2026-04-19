package com.dayu.demo2reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo2 {
    @Test
    public void getClassInfo() {
        // 获取类的信息，并对操作
        // 1. 反射第一步，获取Class对象，代表拿到类
        Class c1 = Student.class;
        System.out.println(c1.getName()); // 类的全类名 com.dayu.demo2reflection.Student
        System.out.println(c1.getSimpleName()); // 类的简称 Student
    }
    @Test
    // 2，获取类的构造器对象并对操作
    public void getConstructorInfo() throws  Exception{
        // 获取类的构造器对象并对其进行操作
        // 1. 获取Class对象
        Class c1 = Dog.class;
        // 2. 获取构造器对象
        Constructor[] cons = c1.getDeclaredConstructors();
        for (Constructor c : cons) {
            System.out.println(c.getName() + "(" + c.getParameterCount() + ")");
        }
        // 3.获取单个构造器
        Constructor con = c1.getDeclaredConstructor(); // 无参构造器
        System.out.println(con.getName() + "(" + con.getParameterCount() + ")");

        Constructor con2 = c1.getDeclaredConstructor(String.class, int.class); // 2个参数的构造器
        System.out.println(con2.getName() + "(" + con2.getParameterCount() + ")");

        // 4.获取构造器的作用依然是创建对象
        // 暴力反射：暴力反射可以访问私有的构造器、方法、属性
        con.setAccessible(true);
        Dog dog1 = (Dog) con.newInstance();
        System.out.println(dog1);

        Dog dog2 = (Dog) con2.newInstance("张三", 10);
        System.out.println(dog2);
    }
    // 3. 获取类的成员变量对象并操作
    @Test
    public void getFieldInfo() throws  Exception{
        // 获取类的成员变量对象并操作
        Class c1 = Dog.class;
        // 2，获取成员变量对象
        Field[] fields = c1.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName() + "(" + field.getType().getName() + ")");
        }
        // 3.获取单个成员变量对象
        Field field = c1.getDeclaredField("name");
        System.out.println(field.getName() + "(" + field.getType().getName() + ")");
        Field field1 = c1.getDeclaredField("hobby");
        System.out.println(field1.getName() + "(" + field1.getType().getName() + ")");

        // 4. 获取成员变量的作用依然是操作成员变量
        Dog d1 = new Dog("张三", 10);
        field.setAccessible(true);
        field.set(d1, "王五");
        System.out.println(d1);

        String name = ( String)field.get(d1);
        System.out.println(name);
    }
    // 4. 获取类的成员方法对象并操作
    @Test
    public void getMethodInfo() throws  Exception{
        // 获取类的成员方法对象并操作
        // 1. 获取Class对象
        Class c1 = Dog.class;
        // 2. 获取成员方法对象
        Method[] methods = c1.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + "(" + method.getParameterCount() + ")");
        }
        // 3. 获取单个成员方法对象
        Method eat = c1.getDeclaredMethod("eat");// 无参eat方法
        Method eat1 = c1.getDeclaredMethod("eat", String.class);// 有参eat方法

        System.out.println(eat.getName() + "(" + eat.getParameterCount() + ")");
        System.out.println(eat1.getName() + "(" + eat1.getParameterCount() + ")");
        // 4. 获取成员方法的作用依然是操作成员方法
        Dog d1 = new Dog("张三", 10);
        eat.setAccessible(true);
        Object invoke = eat.invoke(d1);
        System.out.println(invoke);
        Object invoke1 = eat1.invoke(d1, "张三");
        System.out.println(invoke1);
    }
}
