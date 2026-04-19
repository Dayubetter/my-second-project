package com.dayu.demo2reflection;

public class ReflectDemo1 {
    public static void main(String[] args) throws Exception{
        // 反射第一步：获取类的Class对象。获取类本身
        // 1. 获取类本身 类.class
        Class c1 = Student.class;
        System.out.println(c1);
        // 2. 获取类本身 Class.forName("类的全类名")
        Class c2 = Class.forName("com.dayu.demo2reflection.Student");
        System.out.println(c2);

        // 3. 获取类本身 对象.getClass()
        Student s = new Student();
        Class c3 = s.getClass();
        System.out.println(c3);

        System.out.println(c1 == c2);
        System.out.println(c2 == c3);
    }
}
