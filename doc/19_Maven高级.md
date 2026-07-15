### 分模块设计与开发
#### 分模块设计-策略
1. 按照功能模块拆分：比如：公共组件、商品模块、搜索模块、购物车模块、订单模块等
2. 按层拆分，比如：公共组件、实体类、控制层、业务层、数据访问层
3. 按照功能模块+层拆分
eg: 从tlias中分出来一个tlias-pojo工程，里面存放所有实体类
在tlias的pom中引入Tlias-pojo实体类
```xml
<dependencies>
    <dependency>
        <groupId>com.dayu</groupId>
        <artifactId>tlias-pojo</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```
### 继承与聚合
#### 继承
**继承关系实现：**
1. 创建maven模块tlias-parent，该工程为父工程，设置打包方式pom(默认jar)
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.2.10</version>
<!--    配置父工程的pom.xml的相对路径-->
    <relativePath/>
</parent>


<groupId>com.dayu</groupId>
<artifactId>tlias-parent</artifactId>
<version>1.0-SNAPSHOT</version>
<packaging>pom</packaging>
```
```xml
<parent>
    <groupId>com.dayu</groupId>
    <artifactId>tlias-parent</artifactId>
    <version>1.0-SNAPSHOT</version>
    <relativePath>../tlias-parent/pom.xml</relativePath>
</parent>
```
2. 在子工程的pom.xml中配置继承关系
3. 在父工程中配置各个工程共有的依赖(子工程会自动继承父工程的依赖)

**版本锁定：**
- 在maven中，可以在父工程的pom文件中通过<dependencyManagement>来同一管理依赖版本
- dependencyManagement只管理，不会自动引入
```xml
<!--父工程：-->
<dependencyManagement>
    <dependencies>
<!--        JWT零盘-->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.9.1</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```
```xml
<!--子工程-->
<dependencies>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
    </dependency>
</dependencies>
```

**自定义属性/引用属性:**
```xml

<properties>
    <lombok.version>1.18.30</lombok.version>
    <jjwt.version>0.9.1</jjwt.version>
</properties>

<dependencies>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>${lombok.version}</version>
</dependency>
</dependencies>
```
#### 聚合
- 聚合：将多个模块组织成一个整体，同时进行项目的构建
- 聚合工程：一个不具有业务功能的"空"工程(有且仅有一个pom文件)
- 作用：快速构建项目(无需根据依赖关系手动构建，直接在聚合工程上构建即可)
- 实现：maven中可以通过<modules>标签来指定需要聚合的子模块名称
```xml
<!--聚合：-->
<modules>
    <module>../tlias-pojo</module>
    <module>../tlias-utils</module>
    <module>../tlias-web-management</module>
</modules>  
```
> 聚合工程中所包含的模块，在构建时，会自动根据模块间的依赖关系设置构建顺序，与聚合工程中模块的配置书写位置无关
### 私服
资源上传与下载
1. 设置私服的访问用户名/密码(settings.xml中的servers中配置)
```xml

<server>
    <id>maven-releases</id>
    <username>admin</username>
    <password>admin</password>
</server>

<server>
    <id>maven-snapshots</id>
    <username>admin</username>
    <password>admin</password>
</server>
```
2. IDEA的maven工程的pom文件中配置上传(发布)地址
```xml

<distributionManagement>
    <repository>
        <id>maven-releases</id>
        <url>http://192.168.1.100:8081/repository/maven-releases/</url>
    </repository>
    <snapshotRepository>
        <id>maven-snapshots</id>
        <url>http://192.168.1.100:8081/repository/maven-snapshots/</url>
    </snapshotRepository>
</distributionManagement>
```
3. 设置私服依赖下载的仓库组地址(settings.xml中的mirrors、proxies中配置)
```xml

<mirror>
    <id>maven-public</id>
    <mirrorOf>*</mirrorOf>
    <url>http://192.168.1.100:8081/repository/maven-public/</url>
</mirror>
```
如果是snapshot
```xml

<prfile>
    <id>allow-snapshots</id>
    <activation>
        <activeByDefault>true</activeByDefault>
    </activation>
    <repositories>
        <repository>
            <id>maven-public</id>
            <url>http://192.168.1.100:8081/repository/maven-public/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
<!--            就这里true-->
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
    </repositories>
</prfile>
```