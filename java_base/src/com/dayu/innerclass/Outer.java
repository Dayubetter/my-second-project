package com.dayu.innerclass;

public class Outer {
    // 成员内部类：无static修饰，属于外部类的对象持有
    public class Inner {
        public void show() {
            System.out.println("show");
        }
    }
}
