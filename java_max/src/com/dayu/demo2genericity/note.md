### 泛型类
修饰符 class 类名<类型变量，类型变量，...> {
}
```java
public class ArrayList<E> {}
```

### 泛型接口
修饰符 interface 接口名<类型变量，类型变量，...> {
}

### 泛型方法
修饰符 <类型变量，类型变量，...> 返回值类型 方法名(参数列表) {
}
```java
public static <T> void add(T t) {}  // 泛型方法
public E get(int index) {           // 不是泛型方法
    return (E) arr[index];
}
```

### 通配符
修饰符 <? extends 类型>
<?> 表示任意类型

### 上下限
- <? super 类型> 下限 
- <? extends 类型> 上限

### 泛型支持的类型
泛型不支持基本数据类型，只能支持引用数据类型(对象类型 )

### 包装类
基本数据类型对应的包装类
byte Byte
short Short
int Integer
long Long
float Float
double Double
boolean Boolean
char Character
...

### 包装类新增的功能
1. 把基本类型的数据转换成字符串
2. 把字符串数值转换成对应的基本数据类型