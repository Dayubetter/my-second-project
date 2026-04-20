package com.dayu.demo3annotation;

@MyBook(name = "java", age = 18, address = {"beijing", "shanghai"})
@A("only")
public class AnnotationDemo1 {
    @MyBook(name = "c++", age = 52, address = {"beijing", "shanghai"})
    public static void main( @A("only") String[] args ) {
        // 自定义注解
        @A("only")
        int a = 10;
    }
}
