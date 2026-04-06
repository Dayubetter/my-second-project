package com.dayu.innerclass;

public class InnerClassDemo2 {
    public static void main(String[] args) {
        // 老师、学生都要参加游泳比赛。
        Swim s1 = new Swim() {
            @Override
            public void swimming() {
                System.out.println("Student swimming");
            }
        };
        start(s1);

        System.out.println("===============");

        start(new Swim() {
            @Override
            public void swimming() {
                System.out.println("Teacher swimming");
            }
        });
    }

    // 设计一个方法，可以接收老师，和学生开始比赛
    public static void start(Swim s) {
        System.out.println("start");
        s.swimming();
        System.out.println("end");
    }
}

//class Teacher implements Swim {
//    @Override
//    public void swimming() {
//        System.out.println("Teacher swimming");
//    }
//}
//
//class Student implements Swim {
//    @Override
//    public void swimming() {
//        System.out.println("student swimming");
//    }
//}

interface Swim {
    void swimming();
}
