package com.dayu.demo3collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

public class CollectionDemoLambda {
    public static void main(String[] args) {
        // default void forEach(Consumer<? super T> action)  结合Lambda表达式遍历
        Collection<String> names = new ArrayList<>();

        names.add("Alice");
        names.add("Bob");
        names.add("Tom");
        names.add("Jerry");
        System.out.println(names);

        names.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        System.out.println("----------------");
        names.forEach(s -> System.out.println(s)); // lambda表达式
        System.out.println("----------------");
        names.forEach(System.out::println);  // 方法引用
    }
}
