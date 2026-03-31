package com.dayu.extendsdemo;

public class Test {
    public static void main(String[] args) {
        // 子类可以继承父类中的非私有成员
        // 子类对象其实是由子类和父类共同创建出的对象，子类是完整的
        Teacher teacher = new Teacher();
        teacher.setName("<UNK>");
        teacher.setGender('M');
        teacher.setSkill("java");
        System.out.println(teacher.getName());
        System.out.println(teacher.getGender());
        System.out.println(teacher.getSkill());
    }
}
