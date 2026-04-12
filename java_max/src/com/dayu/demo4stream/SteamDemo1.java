package com.dayu.demo4stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SteamDemo1 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add("David");
        names.add("Edith");
        names.add("Fred");
        names.add("Gina");
        names.add("Helen");
        names.add("Iris");
        names.add("Jerry");
        names.add("Kevin");

        List<String> newNames = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("J") && name.length() == 5) {
                newNames.add(name);
            }
        }
        System.out.println(newNames);

        System.out.println("----------------");
        // 使用Steam流
        List<String> j = names.stream().filter(s -> s.startsWith("J")).filter(s -> s.length() == 5).collect(Collectors.toList());
        System.out.println(j);

    }
}
