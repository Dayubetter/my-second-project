package com.dayu.demo3annotation;

public @interface A {
    String value(); // 特殊属性，在使用时如果默认只有一个属性，可以省略value
    String name() default "小王";
}
