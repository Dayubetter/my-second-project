package com.dayu.innerclass;

public class InnerClassDemo1 {
    public static void main(String[] args) {
        // 成员内部类创建对象的格式：
        // 外部类名称.内部类名称 对象名 = new 外部类名称(). new 内部类名称();
        Outer.Inner oi = new Outer().new Inner();
        oi.show();
        // 成员内部类访问外部类成员的特点
        // 1. 成员内部类中可以直接访问外部类的静态成员，也可以直接访问外部类的实例成员
    }
}
