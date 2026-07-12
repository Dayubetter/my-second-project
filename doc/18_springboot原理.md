### 配置优先级
**配置：**
- SpringBoot中支持三种格式的配置文件
- application.properties
- application.yml
- application.yaml

**优先级：**
application.properties > application.yml > application.yaml

- SpringBoot除了支持配置文件属性配置，还支持**Java系统属性**和**命令行参数**的方式进行属性配置

Java系统属性 java -Dserver.port=9000 -jar xxx.jar 
命令行参数 java -jar xxx.jar --server.port=9000

**优先级：**
命令行参数 > Java系统属性 > application.properties > application.yml > application.yaml

**执行时：**
java -Dserver.port=9000 -jar xxx.jar --server.port=8000 
注：springboot项目进行打包时需要引入 spring-boot-maven-plugin 插件


### Bean管理
**Bean作用域：**
- Spring支持五种作用域，后三种在web环境才生效

|作用域| 描述                               |
|:----|:---------------------------------|
|singleton| 单例模式，SpringBoot默认作用域，容器内同名称的bean |
|prototype| 多例模式，每次调用都会创建一个新的bean |
|request| 作用于web环境，每次http请求都会创建一个新的bean |
|session| 作用于web环境，同一个session会创建同一个bean |
|application| 作用于web环境，整个应用共享一个bean |

```java
@Scope("prototype") // 指定Bean作用域是多例的
@Lazy // 懒加载 延迟初始化-延迟到第一次使用的时候，再创建这个bean，默认是项目启动时创建
@RequesMapping("/dept")
@RestController
public class DeptController { }
```

> 单例的bean是项目启动的时候创建，创建完毕后会将该bean存入IOC容器中
> 
> 多例的bean是项目启动的时候不会创建，第一次使用时创建，创建完毕后会将该bean存入IOC容器中
> 
> bean的线程安全取决于bean的状态及bean的作用域
> - 单例bean：如果是无状态的bean，内部不保存任何状态信息，则是线程安全的
> - 单例bean：如果是有状态的bean,内部会保存状态信息，多个线程会同时操作该bean时，可能会出现数据不一致的问题，这样的bean则是线程不安全的

**第三方的Bean：**
- 如果使用的第三方Bean，是无法使用@Component及衍生注解进行管理，则需要使用@Bean进行管理
```java
@SpringBootApplication
public class Springboot01Application {
    @Bean // 将方法返回值交给IOC容器管理，成为IOC容器的bean对象
    public AliyunOSSOperator aliyunOSSOperator(AliyunOSSProperties aliyunOSSProperties) {
        return new AliyunOSSOperator(aliyunOSSProperties);
    }
}
```
```java
@Configuration // 配置类统一管理，不放启动类
public class CommonConfig {
    @Bean
    public AliyunOSSOperator aliyunOSSOperator(AliyunOSSProperties aliyunOSSProperties) {
        return new AliyunOSSOperator(aliyunOSSProperties);
    }
}
```
> 如果第三方Bean需要依赖其他bean对象，直接再bean定义方法中设置形参即可，容器会根据类型自动装配
> 
> 通过@Bean注解的name或value属性可以声明bean的名称，如果不指定，默认bean的名称就是方法名
### SpringBoot原理
**起步依赖：**
spring-boot-starter-web
spring-boot-starter-aop
spring-boot-starter-test
mybatis-spring-boot-starter
pagehelper-spring-boot-starter
...
以上的起步依赖原理就是依赖传递

**自动配置：**
- SpringBoot的自动配置就是当spring项目启动后，一些配置类、bean对象就自动存入到了IOC容器中，不需要我们手动去声明，从而简化了开发、省去了繁琐的配置操作。
```xml
<dependency>
<groupId>com.google</groupId>
<artifactId>......</artifactId>
<version>0.0.1-SNAPSHOT</version>
</dependency>   
```

```java

@SpringBootTest
class Springboot01WebTests {
    @Autowired
    private Gson gson;

    @Test
    public void TestJson() {
        System.out.println(gson.toJson(Result.success("Hello Gson")));
    }
}
```
> 测试类中@Autowired注入的gson对象，是springboot自动配置的，不需要我们手动去声明

自动配置的实现方案一：
`@ComponentScan(basePackages = {"com.example"},{"com.dayu"}) // 扫描指定包下的所有组件 加了以后，内置的失效，需要手动去添加
@SpringBootApplication // 具备组件扫描功能，但是默认扫描的是启动类所在包及其子包`

自动配置的实现方案二：
@Import导入，@Import导入的类，会自动加入到IOC容器中，导入的形式主要有以下几种：
1. 导入普通类
2. 导入配置类
3. 导入ImportSelector接口实现类

```java
public class TokenParser {
    public void parse() {
        System.out.println("TokenParser.parse()");
    }
}


@Configuration
public class HeaderConfig {
    @Bean
    public HeaderParser headerParser() {
        return new HeaderParser();
    }
    @Bean
    public HeaderGenerator headerGenerator() {
        return new HeaderGenerator();
    }
}

//@Import(TokenParser.class) // 导入普通类
//@Import(HeaderConfig.class) // 导入配置类
//@Import(MyImportSelector.class) // 导入ImportSelector接口实现类-批量导入
@EnableHeaderConfig // 自定义注解，用于批量导入,一般三方包会提供对应的注解，用于批量导入 封装了@Import
@SpringBootApplication
public class SpringbootWebConfigApplication {
    // ...
}
```

**自动配置-源码跟踪：**

**自动配置-@Conditional：**
- 作用：按照一定的条件进行判断，在满足给定条件后才会注册对应的Bean对象到SpringIOC容器中。
- 位置：方法、类
- @Conditional本身是一个父注解，派生出大量的子注解：
  - @ConditionalOnClass：判断环境中是否有对应字节码文件，才注册bean到IOC容器
  - @ConditionalOnMissingBean：判断环境中没有对应bean(类型或名称)，才注册bean到IOC容器
  - @ConditionalOnProperty: 判断配置文件中有对应属性和值，才注册bean到IOC容器
#### 自定义starter
- 场景：在实际开发中，经常会定义一些公共组件，提供给各个项目团队使用。而在springBoot的项目中，一般会将这些公共组件封装为SpringBoot的starter(包含了起步依赖和自动配置的功能)
eg:
```text
Maven:org.springframework.boot:spring-boot-autoconfigure:3.2.6 // 自动配置功能：引入starter依赖，会自动引入自动配置功能
Maven:org.springframework.boot:spring-boot-starter:3.2.6 // 依赖管理功能：引入starter依赖，会自动引入依赖管理功能

Maven:org.mybatis.spring.boot:mybatis-spring-boot-autoconfigure:3.0.3
Maven:org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3

Maven:com.github.pagehelper:pagehelper-spring-boot-autoconfigure:1.4.7
Maven:com.github.pagehelper:pagehelper-spring-boot-starter:1.4.7
```

- 需求：自定义aliyun-oss-spring-boot-starter，完成阿里云OSS操作工具类AliyunOSSOperator的自动配置。
- 目标：引入起步依赖之后，要想使用阿里云OSS，注入AliyunOSSOperator直接使用即可。
- 步骤：
1. 创建aliyun-oss-spring-boot-starter模块
2. 创建aliyun-oss-spring-boot-autoconfigure模块,在starter中引入该模块
3. 在aliyun-oss-spring-boot-autoconfigure模块中定义自动配置功能，并定义自动配置文件META-INF/spring/xxx.imports