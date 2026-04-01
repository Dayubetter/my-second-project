package com.dayu.extendsconstructor;

public class Test {
    public static void main(String[] args) {
        // 子类的全部构造器，都会先调用父类的构造器，再执行自己
        // 如果父类没有无参，用super指定有参
        Son s=new Son();
    }
}

class Son extends Father {
    public Son() {
        super("name"); // 指定父类的有参构造器
        System.out.println("Son's constructor");
    }
}

class Father{
    public Father(){
        System.out.println("Father's constructor");
    }
    public Father(String name){
        System.out.println("Father's param constructor");
    }
}
