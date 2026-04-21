### 动态代理
如何为Java创建一个代理对象
java.lang.reflect.Proxy类：提供了为对象产生代理对象的方法
```java
public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
参数一: 用于指定用哪个类加载器, 去加载生成的代理类
参数二: 指定接口，这些接口用于指定生成的代理长什么，也就是有哪些方法
参数三: 用来指定生成的代理对象要干什么事情
```