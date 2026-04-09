package com.dayu.demo3collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CollectionDemo {
    public static void main(String[] args) {
        // Collection提供的通用集合功能
        Collection<String> list = new ArrayList<>();

        // 添加元素
        list.add("1");
        list.add("2");
        list.add("zz");
        System.out.println(list); // [1, 2, zz]

        // 获取集合的元素个数
        System.out.println(list.size());
        // 删除集合元素
        list.remove("1");
        System.out.println(list);
        // 判断集合是否为空
        System.out.println(list.isEmpty());
        // 清空集合
        list.clear();
        System.out.println(list.isEmpty());

        // 判断集合中是否包含某个元素
        System.out.println(list.contains("2"));

        // 把集合转换成数组
        Object[] arr = list.toArray();
        System.out.println(Arrays.toString(arr));

        String[] arr1 = list.toArray(new String[0]);
        // String[] arr2 = list.toArray(String::new); // 函数引用 11
    }
}
