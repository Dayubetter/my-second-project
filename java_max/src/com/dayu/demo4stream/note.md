### Stream 流的使用步骤
1. 获取Stream流
- Stream流代表一条流水线，并能与数据源建立连接
2. 调用流水线的各种方法，对数据进行处理计算(过滤、排序、去重、映射、归约、匹配、统计)
3. 获取处理的结果，遍历、统计、收集到一个新集合中返回

#### 获取Strea流
- 获取集合的Stream流
Collection提供的方法：`default Stream<E> stream()` 获取当前集合的Stream流
- 获取数组的Stream流
Arrays类提供的方法：`public static <T> Stream<T> stream(T[] array)` 获取指定数组的Stream流
Stream类提供的方法: `public static <T> Stream<T> of(T... values)` 获取当前接收数据的Stream流