package com.dayu.demo3collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class MapTraversalTest {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put("Alice", 18);
        map.put("Bob", 19);
        map.put("Tom", 17);
        map.put("Jerry", 16);
        map.put("Jerry", 20);
        System.out.println(map);

        // 1.
        Set<String> keys = map.keySet();
        for (String key : keys) {
            Integer value = map.get(key);
            System.out.println(key + "=" + value);
        }
        System.out.println("-------------------------------------");

        // 2.增强for
        // 把Map集合转换为Set集合，里面的元素类型都是键值对类型Map.Entry<String, Integer>
        /**
         * map = {Tom=17, Bob=19, Alice=18, Jerry=20}
         * ↓
         * map.entrySet()
         * ↓
         * Set<Map.Entry<String, Integer>> entries = [(Tom=17),(Bob=19),(Alice=18),(Jerry=20)]
         */

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "=" + value);
        }
        System.out.println("-------------------------------------");
        // 3.lambda遍历
        map.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s + "=" + integer);
            }
        });

        System.out.println("-------------------------------------");
        map.forEach((k,v) -> System.out.println(k + "=" + v));

    }
}
