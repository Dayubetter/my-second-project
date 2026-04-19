package com.dayu.demo2reflection;

public class Dog {
    private String name;
    private int age;
    private String hobby;
    private Dog() {
        System.out.println("无参构造器");
    }
    public Dog(String name, int age) {
        System.out.println("有参构造器");
        this.name = name;
        this.age = age;
    }
    public void show() {
        System.out.println("name:" + name + " age:" + age);
    }
    private void eat(){
        System.out.println("吃吃吃");
    }
    public String eat(String name) {
        System.out.println("吃吃吃" + name);
        return  "汪汪汪";
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
