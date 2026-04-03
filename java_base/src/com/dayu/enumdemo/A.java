package com.dayu.enumdemo;

public enum A {
    // 枚举类的第一行只能罗列枚举类的对象名称，本质是常量，每个常量都会记住枚举类的一个对象
    // 枚举类都是final类，不可以被继承，都继承自java.lang.Enum类
    // 构造器是私有的，对外不能创建对象
    // 编译器为枚举类新增几个方法
    // 枚举类 做信息的分类和标志

    X,Y,Z;

}
