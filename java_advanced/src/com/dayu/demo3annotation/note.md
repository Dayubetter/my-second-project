### 注解概述-自定义注解
自定义注解
就是自己定义注解
public @interface 注解名{
    public 属性类型 属性名() default 默认值;
}

### 注解概述-注解的原理
```java
public @interface MyTest1{
    String aaa();
    boolean bbb();
    String[] ccc();
}
```
          |
```java
public interface MyTest1 extends Annotation {
    public abstract String aaa();
    public abstract boolean bbb();
    public abstract String[] ccc();
}
```
### 注解概述-注解的元注解
元注解：注解的注解

### 注解的解析
判断类上、方法上、成员变量上是否存在注解，并把注解里的内容给解析出来
**如何解析注解？**
- 指导思想：要解析谁上面的注解，就应该先拿到谁
- 比如要解析类上面的注解，则应该先获取该类的Class对象、再通过Class对象解析其上面的注解
- 比如要解析成员方法上的注解、则应该获取到该成员方法的Method对象、再通过Method对象解析其上面的注解
- Class、Method、Field、Constructor、都实现了AnnotatedElement接口，他们都拥有解析注解的能力

|AnnotatedElement接口提供了解析注解的方法| 说明|
|:--|:--|
|public Annotation getDeclaredAnnotation() |获取当前对象上面的注解|
|public T getDeclaredAnnotation(Class<T> annotationClass) | 获取指定的注解对象|
|public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) | 判断当前对象是否包含指定的注解|