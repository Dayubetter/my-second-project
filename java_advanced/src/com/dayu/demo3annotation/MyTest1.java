package com.dayu.demo3annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 注解保留策略, 运行时保留
@Target(ElementType.METHOD)  // 作用于方法上
public @interface MyTest1 {
}
