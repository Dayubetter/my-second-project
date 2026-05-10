mybatis持久层接口，定义Sql 注解/xml

```java
spring.datasource.url=jdbc:mysql://localhost:3306/web
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=
```

```java
@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    public List<User> findAll();
}
```

### 数据库连接池
- 数据库连接池时一个容器，负责分配，管理数据库连接(Connection)
- 它允许应用程序重复使用一个现有的数据库连接，而不是再重新建立一个
- 释放空闲时间超过最大空闲时间的连接，来避免因为没有释放连接而引起的数据库连接遗漏

### 删除用户 - DELETE
- Mybatis中的#号与$ 号:
1. #{...} 占位符。执行时会将#{...}替换为?,生成预编译SQL         参数值传递     安全、性能高(推荐)
2. ${...} 拼接符。直接将参数拼接在SQL语句中，存在SQL注入问题      表名、字段名动态设置时使用    不安全、性能低
### 新增用户 - insert
- 需求：添加用户
- SQL：insert into user（id,username,password,is_admin) values(null,'admin','123456','1')

### 修改用户 - update
- 需求：修改用户
- SQL：update user set username = 'admin',password = '123456',is_admin = '1' where id = 1

### 查询用户- select
- 需求：根据用户名和密码查询用户信息
- SQL：select * from user where username = 'admin' and password = '123456'

> 默认情况下，接口方法中在编译成字节码文件当中，形参并不会保留

### XML映射配置
使用Mybatis的注解方式，主要是来完成一些简单的增删改查功能。如果需要实现复杂的SQL功能，建议使用XML来配置映射语句，也就是将SQL语句写在XML配置文件中。

在Mybatis中使用XML映射文件方式开发，需要符合一定的规范：
1. XML映射文件的名称与Mapper接口名称一致，并且将XML映射文件和Mapper接口放置在相同包下（同包同名）
2. XML映射文件的namespace属性为Mapper接口全限定名一致
3. XML映射文件中sql语句的id与Mapper接口中的方法名一致，并保持返回类型一致。

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="">
</mapper>
```