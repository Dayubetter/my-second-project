### AOP面向切面编程
AOP（Aspect Oriented Programming）面向切面编程，是一种程序设计范式，它把那些与业务逻辑无关，却为业务逻辑所共同调用的代码抽取出来，并封装成可重用的模块，从而让业务逻辑更Clean。

- 可以理解为面向特定方法编程

- 场景：案例中部分业务方法运行较慢，定位执行耗时较长的方法，此时需要统计每一个业务方法的执行耗时
优势：
- 减少重复代码
- 代码无侵入
- 提高开发效率
- 维护方便

快速入门：统计所有业务层方法的执行耗时
1.导入依赖：在pom文件中引入AOP的依赖
`<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-aop</artifactId>
</dependency>`
2.编写AOP程序：针对于特定方法根据业务需要进行编程
```java
@Aspect
@Component
public class RecordTimeAspect {
    @Around("execution(* com.dayu.service.impl.*.*(..))")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("方法{}耗时:{}ms",joinPoint.getSignature().getName(), end - begin);
        return result;
    }
}
```
应用场景：
- 统计所有业务方法执行耗时
- 事务管理
- 权限控制

#### AOP核心概念
- 连接点：JoinPoint，可以被AOP控制的方法(暗含方法执行时的相关信息)
- 通知：Advice，指那些重复的逻辑，也就是共性功能(最终体现为一个方法)
- 切入点：Pointcut，匹配连接点的条件，通知仅会在切入点方法执行时被应用
- 切面：Aspect，描述通知与切入点的对应关系(通知+切入点)
- 目标对象：Target，通知所应用的对象

#### AOP执行流程
1. 创建代理对象
2. Autowired注入的时代理对象，执行代理对象中的方法，代理对象中运行的方法是实际业务中方法，代理对象中运行方法时，会调用通知方法

#### AOP进阶
**通知类型：**
- 根据通知方法执行的时机的不同，将通知类型分为以下常见的五类：
1. @Around：环绕通知，此注解标注的通知方法在目标方法前、后都被执行
2. @Before：前置通知，此注解标注的通知方法在目标方法执行前被执行
3. @After：后置通知，此注解标注的通知方法在目标方法执行后被执行，无论是否有异常都会执行
4. @AfterReturning：返回通知，此注解标注的通知方法在目标方法正常返回后被执行，仅当目标方法没有异常时执行，有异常不执行
5. @AfterThrowing：异常通知，此注解标注的通知方法在目标方法抛出异常后被执行

注意：@Around环绕通知需要自己调用ProceedingJoinPoint.proceed()来让原始方法执行，其他通知不需要考虑目标方法执行

#### @PointCut
该注解的作用是将公共的切点表达式抽取出来，需要用到时引用该切点表达式即可。
```java
@Pointcut("execution(* com.dayu.service.impl.*.*(..))")
public void pointCut() {}

@Around("pointCut()")
public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {}
```
**通知顺序：**
- 当有多个切面的切入点都匹配到了目标方法，目标方法运行时，多个通知方法都会被执行
- 执行顺序
  - 不同切面类中，默认按照切面类的**类名字母排序：**
    - 目标方法前的通知方法：字母排名靠前的先执行
    - 目标方法后的通知方法：字母排名靠前的后执行

@Order(数字) 加在切面类上来控制顺序
- 目标方法前的通知方法:数字小的先执行
- 目标方法后的通知方法:数字小的后执行

**切入点表达式：**
- 介绍：描述切入点方法的一种表达式
- 作用：用来决定项目中的哪些方法需要加入通知
- 常见形式：
1. execution(.....):根据方法的签名来匹配   
- execution(访问修饰符？返回值 包名.类名.?方法名(参数列表) throws 异常?)  带？可以省略
- 可以使用通配符描述切入点
  - * ：单个独立的任意符号，可以匹配任意返回值、包名、类名、方法名、任意类型的一个参数，也可以通配包、类、方法名的一部分
  - ..：多个连续的任意符号，可以统配任意层级的包，或任意类型、任意个数的参数 execution( * com.dayu.service.impl..*.*(..))
2. @annotation(.....):根据方法上的注解来匹配
```java
@Before("execution(* com.dayu.service.impl.DeptServiceImpl.delete(java.lang.Integer))")
public void before(JoinPoint joinPoint) {}
```
```java
@Before("@annotation(com.dayu.annotation.MyLog)")
public void before() {}
```
**连接点：**
- 在spring中用JoinPoint抽象了连接点，用它可以获得方法执行时的相关信息，如目标类名、方法名、方法参数等。
  - 对于@Around通知，获取连接点信息只能使用ProceedingJoinPoint
  - 对于其他四种通知，获取连接点信息只能使用JoinPoint,它是ProceedingJoinPoint的父类

```java
@Around("execution(* com.dayu.service.impl.*.*(..))")
public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
  String className = joinPoint.getTarget().getClass().getName(); // 获取目标类名
  Signature signature = joinPoint.getSignature(); // 获取目标方法签名
  String methodName = joinPoint.getSignature().getName(); // 获取目标方法名
  Object[] args = joinPoint.getArgs(); // 获取目标方法参数
  Object result = joinPoint.proceed(); // 执行目标方法,获取返回值(环绕通知)
  return result;
}
```

```java

@Before("execution(* com.dayu.service.impl.*.*(..))")
public void before(JoinPoint joinPoint) {
  String className = joinPoint.getTarget().getClass().getName(); // 获取目标类名
  Signature signature = joinPoint.getSignature(); // 获取目标方法签名
  String methodName = signature.getName(); // 获取目标方法名
  Object [] args = joinPoint.getArgs(); // 获取目标方法参数
}
```
将案例中增删改相关接口的操作日志记录到数据库表中
- 日志信息包含：操作人、操作时间、执行方法的全类名、执行方法名、方法运行时参数、返回值、方法执行时长

```mysql
create table operate_log(
 id int unsigned primary key auto_increment comment 'ID',
 operate_emp_id int unsigned comment '操作人ID',
 operate_time datetime comment '操作时间',
 class_name varchar(100) comment '操作的类名',
 method_name varchar(100) comment '操作的方法名',
 method_params varchar(1000) comment '方法参数',
 return_value varchar(2000) comment '返回值',
 cost_time int comment '方法执行耗时, 单位:ms'
) comment '操作日志表';
```

**ThreadLocal**
- ThreadLocal并不是一个Thread，而是Thread的局部变量
- ThreadLocal为每个线程提供单独的变量副本(线程内共享变量)，线程隔离，每个线程都可以独立地改变自己的副本，而不会影响其它线程的副本
- ThreadLocal常用方法：
  - public void set(T value) 设置当前线程的局部变量的值
  - public T get()           返回当前线程所对应的线程局部变量的值
  - public void remove()     移除当前线程的线程局部变量

**获取当前登录员工：**
具体操作步骤：
1. 定义一个ThreadLocal操作的工具类，用于操作当前登录员工ID。
2. 在TokenFilter中，从请求头中获取当前登录员工ID，并设置到ThreadLocal中。(用完之后需要将其删除)
3. 在AOP程序中，从ThreadLocal中获取当前登录员工的ID