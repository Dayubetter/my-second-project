### 线程
线程是一个程序内部的一条执行流程
### 多线程
多线程是指软硬件上实现的多条执行流程的技术（多条线程由CPU负责调度执行）

- 创建线程
1. 继承Thread类，重写run方法，创建对象调用start方法启动线程
主线程不能放在子线程之前
    >缺点：线程已经继承thread类，不能继承其他类,不利于功能的扩展
2. 声明一个实现Runnable接口的类，该类实现run方法。然后可以分配类的实例，在创建Thread时作为参数传递，然后启动
    > 优点：任务类只是实现接口，可以继续继承其他类、实现其他接口、扩展
     缺点：需要多一个Runnable对象。如果线程有执行结果是不能直接返回的
3. 使用Callable接口和FutureTask类实现
假如前两种线程执行完毕后有一些数据需要返回，他们重写的run方法均不能直接返回结果
   1. 创建任务对象
   定义一个类实现Callable接口，重写call方法,封装要做的事情，和要返回的数据
   把Callable任务对象封装成FutureTask(线程任务对象)
   2. 把线程任务对象交给Thread对象
   3. 调用Thread对象的start方法启动线程
   4. 线程执行完毕后、通过FutureTask对象的get方法去获取线程任务执行的结果
### Thread的常用方法
| 方法                                    | 说明                 |
|:--------------------------------------|:-------------------|
| public void run()                     | 线程的任务方法            |
| public void start()                   | 启动线程               |
| public String getName()               | 获取线程名称,默认Thread-索引 |
| public void setName(String name)      | 设置线程名称             |
| public static Thread currentThread()  | 获取当前线程对象           |
| public static void sleep(long millis) | 线程休眠多少毫秒           |
| public final void join()...           | 让调用当前这个方法的线程先执行完   |


| Thread提供的常见构造器                               | 说明                                     |
|:---------------------------------------------|:---------------------------------------|
| public Thread(String name)                   | 创建一个线程对象，并指定名称                         |
| public Thread(Runnable target)               | 封装Runnable对象成为线程对象                     |
| public Thread(Runnable target, String name)  | 封装Runnable对象成为线程对象，并指定线程名称             |

