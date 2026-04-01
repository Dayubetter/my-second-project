package com.dayu.polymorphsm;

public class Wolf extends Animal{
    String name = "Wolf";
    @Override
    public void run() {
        System.out.println("Wolf run");
    }

    public void eatSheep(){
        System.out.println("Wolf eatSheep");
    }
}
