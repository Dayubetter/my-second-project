package com.dayu.demo2genericity;

import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {
        // 需要对学生数据/老师数据都要进行增删改查操作
        StudentData studentData = new StudentData();
        studentData.add(new Student());
        studentData.remove(new Student());
        Student student = studentData.query(1);

        // 泛型方法
        String[] arr = {"1", "2", "3"};
        printArry(arr);
        Student[] students = {new Student(), new Student()};
        printArry(students);

        // 泛型和集合不支持基本数据类型，只能支持对象类型
        // ArrayList<int> list = new ArrayList<>();
        ArrayList<Student> list = new ArrayList<>();
        // 泛型擦除 ：泛型参数T被擦除为Object
        // 把基本数据类型变成包装类对象
        // 手工包装
        // Integer i = new Integer(1); // 过时
        Integer it = Integer.valueOf(1); // 推荐使用

        // 自动装箱 ：基本数据类型变成对应的包装类对象
        int i = 1;
        Integer i1 = i;
        // 自动拆箱 ：包装类对象变成对应的基本数据类型
        int i2 = i1;
        System.out.println(i2);

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1); // 自动装箱
        int i3 = list1.get(0); // 自动拆箱
        System.out.println("-------------------------------------");
        // 1. 把基本类型的数据转换成字符串
        int j = 12;
        String s = Integer.toString(j);
        System.out.println(s+1);

        String s1 = j + "";
        System.out.println(s1+1);
        System.out.println("-------------------------------------");
        // 2. 把字符串转换成基本类型数据
        String s2 = "12";
        // int i4 = Integer.parseInt(s2);
        int i4 = Integer.valueOf(s2);

        System.out.println(i4+1);
    }

    public static <T> void printArry(T[] arr) {
        for (T t : arr) {
            System.out.println(t);
        }
    }

    public static <T> T get(T t) {
        return t;
    }
}
