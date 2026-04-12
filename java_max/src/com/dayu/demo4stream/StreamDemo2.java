package com.dayu.demo4stream;

import java.util.*;
import java.util.stream.Stream;

public class StreamDemo2 {
    public static void main(String[] args) {
        // 1，获取集合的Strea流,调用集合提供的stream()方法
        Collection<String> list = new ArrayList<>();
        Stream<String> s1 = list.stream();

        // 2.Map集合的Stream流
        Map<String, Integer> map = new HashMap<>();
        // 获取键流
        Stream<String> s2 = map.keySet().stream();
        // 获取值流
        Stream<Integer> s3 = map.values().stream();
        // 获取键值对流
        Stream<Map.Entry<String, Integer>> s4 = map.entrySet().stream();

        // 3.数组的Stream流
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eve"};
        Stream<String> s5 = Stream.of(names);
        System.out.println(s5.count());

        Stream<String> s6 = Stream.of("Alice", "Bob", "Charlie", "David", "Eve");
        System.out.println(s6.count());

        System.out.println("------------------");
        List<Double> scores = new ArrayList<>();
        scores.add(80.5);
        scores.add(90.5);
        scores.add(100.5);
        scores.add(95.5);
        scores.add(95.5);
        scores.add(32.5);
        scores.stream().sorted().forEach(System.out::println); // 默认是升序
        System.out.println("------------------");
        scores.stream().sorted((a,b)->Double.compare(b , a)).forEach(System.out::println); // 降序
        System.out.println("------------------");
        scores.stream().sorted((a,b)->Double.compare(b,a)).skip(2).forEach(System.out::println); // 跳过2个
        System.out.println("------------------");
        scores.stream().sorted((a,b)->Double.compare(b,a)).limit(2).forEach(System.out::println); // 获取2个
        System.out.println("------------------");
        // 如果希望自定义对象可以去重，重写对象的hashCode()和equals()方法，才可以去重
        scores.stream().sorted((a,b)->Double.compare(b,a)).distinct().forEach(System.out::println); // 去重
        System.out.println("------------------");
        // 映射/加工方法：把流上原来的数据拿出来变成新数据又放到流上去
        scores.stream().map(s->"add 10：" + (s+10)).forEach(System.out::println);

        // 合并流
        Stream<String> s7 = Stream.of("Alice", "Bob", "Charlie", "David", "Eve");
        Stream<Integer> s8 = Stream.of(11, 22, 33, 44, 55);

        Stream<Object> s9 = Stream.concat(s7, s8);
        s9.forEach(System.out::println);
    }
}
