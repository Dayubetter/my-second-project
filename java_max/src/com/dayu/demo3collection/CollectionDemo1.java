package com.dayu.demo3collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class CollectionDemo1 {
    public static void main(String[] args) {
        // Collection提供的通用集合功能
        Collection<String> names = new ArrayList<>();

        names.add("Alice");
        names.add("Bob");
        names.add("Tom");
        names.add("Jerry");
        System.out.println(names);

        // 得到集合的迭代器对象
        Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            String name = it.next();
            System.out.println(name);
        }
        System.out.println("----------------");
        // 既可以集合也可以数组
        for (String name : names) {
            System.out.println(name);
        }
    }
}
