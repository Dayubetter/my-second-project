package com.dayu.polymorphsm;

public class Test {
    public static void main(String[] args) {
        // 多态时在继承/实现情况下的一种现象，表现为：对象多态、行为多态
        // ！！！存在父类引用子类对象，方法重写
        // 1.对象多态 行为多态
        // 方法：编译看左边，运行看右边
        // 成员变量：编译运行都看左边
        Animal animal1 = new Wolf();
        animal1.run();
        System.out.println(animal1.name);  // 打印动物

        Animal animal2 = new Tortoise();
        animal2.run();
        System.out.println(animal2.name); // 打印动物
        System.out.println("===================");
        Wolf wolf = new Wolf();
        go(wolf);
        Tortoise tortoise = new Tortoise();
        go(tortoise);

    }
    public static void go (Animal animal){
        animal.run();
        // animal.eatShepp(); // 报错，多态下不能调用子类独有功能
        // 强制类型转换可以解决多态下调用子类独有功能
        // java建议强制类型转换前，应该判断对象的真实类型，再进行强制类型转换
        if(animal instanceof Wolf){
            Wolf wolf = (Wolf) animal;
            wolf.eatSheep();
        }else if(animal instanceof Tortoise){
            Tortoise tortoise = (Tortoise) animal;
            tortoise.upHead();
        }
    }
}
