package com.dayu.polymorphsm;

public class Tortoise extends Animal{
    String name = "Tortoise";
    @Override
    public void run() {
        System.out.println("Tortoise run");
    }
    public void upHead(){
        System.out.println("Tortoise upHead");
    }
}
