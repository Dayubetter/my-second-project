package com.dayu.demo3annotation;

@MyTest2(value = "hello",price = 10,address = {"beijing", "shanghai"})
public class Demo {

    @MyTest2(value = "method",price = 10,address = {"beijing", "shanghai"})
    public void show() {

    }
}
