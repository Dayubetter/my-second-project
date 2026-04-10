package com.dayu.demo3collection;

import java.util.ArrayList;
import java.util.Iterator;

public class CollectionTraversalTest {
    public static void main(String[] args) {
        // 认识并发修改异常问题，搞清楚每种遍历的区别
        ArrayList<String> list = new ArrayList<>();
        list.add("1,2,3");
        list.add("2,3,4,5");
        list.add("1,3,5");
        list.add("1,2,4,5");
        list.add("1,2,3,4");
        System.out.println(list);

        // 删除全部包含5的元素
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.contains("5")){
                list.remove(s);
            }
        }
        System.out.println(list);
        // [1,2,3, 2,3,4,5, 1,3,5, 1,2,4,5, 1,2,3,4]
        // [1,2,3, 2,3,4,5, 1,3,5, 1,2,4,5, 1,2,3,4]
        //    i
        // [1,2,3, 1,3,5, 1,2,3,4]
        // 原因删除一个后，后面的补上来，i往后移动一位
        System.out.println("===========================================");
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("1,2,3");
        list2.add("2,3,4,5");
        list2.add("1,3,5");
        list2.add("1,2,4,5");
        list2.add("1,2,3,4");
        System.out.println(list2);

        // 删除全部包含5的元素
        for (int i = 0; i < list2.size(); i++) {
            String s = list2.get(i);
            if (s.contains("5")){
                list2.remove(s);
                i--;
            }
        }
        System.out.println(list2);
        System.out.println("===========================================");
        ArrayList<String> list3 = new ArrayList<>();
        list3.add("1,2,3");
        list3.add("2,3,4,5");
        list3.add("1,3,5");
        list3.add("1,2,4,5");
        list3.add("1,2,3,4");
        System.out.println(list3);

        // 删除全部包含5的元素
        for (int i = list3.size() - 1; i >=0; i--){
            String s = list3.get(i);
            if (s.contains("5")){
                list3.remove(s);
            }
        }
        System.out.println(list3);
        System.out.println("===========================================");
        ArrayList<String> list4 = new ArrayList<>();
        list4.add("1,2,3");
        list4.add("2,3,4,5");
        list4.add("1,3,5");
        list4.add("1,2,4,5");
        list4.add("1,2,3,4");
        System.out.println(list4);

        // 删除全部包含5的元素
        // 迭代器遍历删除也默认存在并发修改异常
        // 使用迭代器遍历删除
        Iterator<String> it = list4.iterator();
        while (it.hasNext()){
            String s = it.next();
            if (s.contains("5")){
                // list4.remove(s);
                it.remove();
            }
        }

        System.out.println(list4);
        System.out.println("===========================================");
        ArrayList<String> list5 = new ArrayList<>();
        list5.add("1,2,3");
        list5.add("2,3,4,5");
        list5.add("1,3,5");
        list5.add("1,2,4,5");
        list5.add("1,2,3,4");
        System.out.println(list5);

        // 删除全部包含5的元素
        // 增强for遍历删除 和lambda都  不能解决并发异常问题
//        for (String s : list5){
//            if (s.contains("5")){
//                 list5.remove(s);
//            }
//        }

//        list5.forEach(s -> {
//            if (s.contains("5")){
//                list5.remove(s);
//            }
//        });
        System.out.println(list5);

    }
}
