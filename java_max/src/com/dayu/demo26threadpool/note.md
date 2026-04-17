### 线程池
线程池可以复用线程
#### 创建线程池
1. JDK5.0 提供了线程池的接口：ExecutorService
2. - 方式一：使用ExecutorService的实现类ThreadPoolExecutor自创建一个线程池对象 
- 方式二：使用Executors(线程池的工具类) 调用方法返回不同特点的线程池对象

方法一：通过ThreadPoolExecutor创建线程池

```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

// ThreadPoolExecutor类提供的构造器
// 使用指定的初始化参数创建一个新的线程池对象
public ThreadPoolExecutor(int corePoolSize,                     // 指定线程池的核心线程的数量   正式工：3
                          int maximumPoolSize,                  // 指定线程池的最大线程数量   总工：5 临时工：2
                          long keepAliveTime,                   // 指定临时线程的存活时间     临时工多久开除
                          TimeUnit unit,                        // 指定临时线程存活的时间单位(秒、分、时、天)
                          BlockingQueue<Runnable> workQueue,    // 指定线程池的任务队列 客人排队的地方
                          ThreadFactory threadFactory,          // 指定线程池的线程工厂    负责招聘员工的hr
                          RejectedExecutorHandler handler)      // 指定线程池的任务拒绝策略(线程都在忙，任务队列也满了的时候，新任务来了该怎么处理

```

#### ExecutorService的常用方法
1. void execute(Runnable command) ：执行任务，没有返回结果，没有异常处理
2. Future<T> submit(Callable<T> task) ：执行任务，返回未来任务对象，用于获取线程返回的结果
3. void shutdown() ：关闭线程池，不再接受新的任务，但会等待正在执行的任务完成
4. List<Runnable> shutdownNow() ：关闭线程池，不再接受新的任务，并立即返回正在执行的任务列表

#### 临时线程的创建时机
1. 新任务提交时发现核心线程都在忙，任务队列也满了，并且还可以创建临时线程，此时才会创建临时线程
2. 核心线程和临时线程都在忙，任务队列也满了，新的任务过来的时候才会开始拒绝任务
- 任务拒绝策略
  - ThreadPollExecutor.AbortPolicy()  丢弃任务并抛出RejectedExecutionException异常。默认策略
  - ThreadPollExecutor.DiscardPolicy()   丢弃任务，但是不抛出异常，这是不推荐的做法
  - ThreadPollExecutor.DiscardOldestPolicy()   丢弃队列中最老的任务，然后重新尝试执行任务
  - ThreadPollExecutor.CallerRunsPolicy()     由主线程负责调用任务的run()方法从而绕过线程池直接执行

#### 使用Executors创建线程池
方式二：使用Executors(线程池的工具类) 调用方法返回不同特点的线程池对象
- 是一个线程池的工具类，提供了很多静态方法用于返回不同特点的线程池对象

| 方法名称                                                                           | 描述                                            |
|:-------------------------------------------------------------------------------|:----------------------------------------------|
| public static ExecutorService newFixedThreadPool(int nThreads)                 | 创建固定线程数量的线程池，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程代替它 |
| public static ExecutorService newSingleThreadExecutor()                        | 创建只有一个线程的线程池对象，如果该线程出现异常而结束，那么线程池会补充一个新线程     |
| public static ExecutorService newCachedThreadPool()                            | 线程数量随着任务增加而增加，如果线程任务执行完毕且空闲了60s则会被回收掉         |
| public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize)| 创建一个线程池，可以实现在给定的延迟后允许任务，或者定期执行任务              |

Executors使用可能存在的陷阱
大型并发系统环境中使用Executors如果不注意可能额会出现系统风险
alibabaJava开发手册：
4. 【强制】线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的允许规则，规避资源耗尽的风险。
说明：Executors返回的线程池对象的弊端如下：
- FixedThreadPool和SingleThreadPool：允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
- CachedThreadPool：允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。

### 并发/并行
进程：一个程序在计算机中的执行过程 线程：进程的分支，进程可以有多个线程
进程中的多个线程其实是并发和并行执行的。
1. 并发：进程中的线程是由CPU负责调度执行的，但是CPU能同时处理线程的数量有限，为了保证全部线程都能往前执行，CPU会轮询为系统的每个线程服务，由于CPU切换的速度很快，给我们的感受就是这些线程在同时执行，这就是并发
2. 并行：在同一个时刻上，同时有多个线程在CPU调度执行   CPU（4核） 4个线程 4个4个的切换并发 