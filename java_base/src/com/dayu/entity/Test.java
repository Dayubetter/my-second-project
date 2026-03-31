package com.dayu.entity;

public class Test {
    public static void main(String[] args) {
        // 实体类 类中的成员变量全部私有，并提供public修改getter/setter
        // 类中需要提供一个无参构造器，有参可选
        // 实体类的基本作用：创建它的对象，存取数据（封装数据）
        Student s1 = new Student();
        s1.setChinese(100);
        s1.setName("<UNK>");
        s1.setMath(100);
        Student s2 = new Student("sss",1,2);
        System.out.println(s2.getMath());
        System.out.println(s2.getChinese());
        System.out.println(s2.getName());
        // 实体类开发中的应用场景
        // 实体类的对象值负责数据存取，其他业务处理交给其他类对象，以实现数据和数据业务处理分离
        // 创建一个学生的操作对象，专门对学生对象的数据进行业务处理
        StudentService studentService = new StudentService(s2);
        studentService.printTotalScore();
        studentService.printAverageScore();
    }
}
