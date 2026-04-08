package com.dayu.lambda;

public class Test {
    public static void main(String[] args) {
        Animal animal = new Animal() {
            @Override
            public void cry() {
                System.out.println("cry...");
            }
        };
        animal.cry();
        System.out.println("===================");
        Swim swim = () -> {
            System.out.println("swimming...");
        };
        swim.swimming();
    }
}

abstract class Animal{
    public abstract void cry();
}

// 函数式接口，只有一个抽象方法的接口  声明函数式接口的注解
@FunctionalInterface
interface Swim {
    void swimming();
}
