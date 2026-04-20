### 反射Reflection
- 反射就是：加载类，并允许以编程的方式解剖类中的各种成分(成员变量、方法、构造器等)。
反射需要学：
1. 学习获取类的信息、操作他们
反射第一步：加载类、获取类的字节码：Class对象
获取类的构造器：Constructor对象
获取类的成员变量：Field对象
获取类的成员方法：Method对象
> 获取Class对象的三种方式
> 
> 1. Class c1 = 类名.class;
> 
> 2. 调用Class提供的方法：public static Class forName(String path)
> 
> 3. Object提供的方法：public Class getClass(); Class c3 = 对象.getClass();

#### 反射获取类中的成分并操作
Class提供了从类中获取构造器的方法

| 方法| 描述 |
| --- | --- |
|Constructor<?>[] getConstructors()|获取全部构造器(只能获取public修饰的)|
|Constructor<?>[] getDeclaredConstructors()|获取全部构造器(包括private、protected、default、public修饰的)|
|Constructor<?> getConstructor(Class<?>... parameterTypes)|获取指定参数的构造器(只能获取public修饰的)|
|Constructor<?> getDeclaredConstructor(Class<?>... parameterTypes)|获取指定参数的构造器(包括private、protected、default、public修饰的)|
获取类构造器的作用：依然是初始化对象返回

|Constructor提供的方法|描述|
| --- | --- |
|T newInstance(Object... initargs) | 调用此构造器对象表示的构造器，并传入参数，完成对象的初始化并返回|
|public void setAccessible(boolean flag) | 设置为true，表示禁止检查访问控制(暴力反射)|

#### 反射的基本作用
可以得到一个类的全部成分然后可以操作
可以破坏封装性
可以绕过泛型的约束
