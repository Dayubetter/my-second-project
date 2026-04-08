package com.dayu.lambda;
import java.util.Arrays;
import java.util.Comparator;

public class Demo {
    public static void main(String[] args) {
        // 给数组排序，匿名内部类
        Student[] students = new Student[6];
        students[0] = new Student("Alice",12,170,"M");
        students[1] = new Student("Bob",18,180,"M");
        students[2] = new Student("Carl",19,190,"M");
        students[3] = new Student("David",20,175,"M");
        students[4] = new Student("Jack",21,190,"M");
        students[5] = new Student("Jack",21,190,"M");

        // 排序
        // public static <T> void sort(T[] a, Comparator<? super T> c)
        // 参数一：需要排序的数组   参数二：给sort声明一个比较器对象（指定排序的规则）
//        Arrays.sort(students,new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                // 排序规则：如果左边对象大于右边对象，返回正整数  如果小于返回负整数  相等返回0
//                return o1.getAge() - o2.getAge();
//            }
//        });

//        Arrays.sort(students, (o1, o2) -> {
//            // 排序规则：如果左边对象大于右边对象，返回正整数  如果小于返回负整数  相等返回0
//            return o1.getAge() - o2.getAge();
//        });

//        Arrays.sort(students, (o1, o2) ->  o1.getAge() - o2.getAge());
        // 静态方法引用
        Arrays.sort(students, Student::compareByAge);

        // 遍历数组中的学生对象并输出
        for (int i = 0; i<students.length; i++){
            Student s = students[i];
            System.out.println(s);
        }
    }
}
