package com.dayu.demo2reflection;

public class ReflectDemo4 {
    public static void main(String[] args) throws  Exception{
        // 反射的应用  框架
        // 对于任意一个对象，该框架都可以把对象的字段名和对应的字段值，保存到文件中去
        // 1。 定义一个方法，可以接收任意对象
        // 2. 每收到一个对象后，使用反射获取该对象的Class对象，然后获取全部的成员变量
        // 3。 遍历成员变量，然后提取成员变量在该对象中的具体值
        // 4，把成员名，和其值，写出到文件中去
        Dog dog = new Dog("小花", 5);
        SaveObjectFrameWork.saveObject(dog);

        Student student = new Student("小王", 18);
        SaveObjectFrameWork.saveObject(student);

        Teacher teacher = new Teacher("小李", 30,"nan", "java","大学" ,"北京", "12345678" , dog);
        SaveObjectFrameWork.saveObject(teacher);
    }
}
