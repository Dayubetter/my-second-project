目录	含义
/bin	存放二进制可执行文件
/boot	存放系统引导时使用的各种文件
/dev	存放设备文件
/etc	存放系统配置文件
/home	存放系统用户的文件
/lib	存放程序运行所需的共享库和内核模块
/opt	额外安装的可选应用程序包所放置的位置
/root	超级用户目录
/sbin	存放二进制可执行文件，只有root用户才能访问
/tmp	存放临时文件
/usr	存放系统应用程序
/var	存放运行时需要改变数据的文件，例如日志文件

操作	指令
查看防火墙状态	systemctl status firewalld / firewall-cmd --state
暂时关闭防火墙	systemctl stop firewalld
永久关闭防火墙(禁用开机自启)	systemctl disable firewalld
暂时开启防火墙	systemctl start firewalld
永久开启防火墙(启用开机自启)	systemctl enable firewalld
开放指定端口	firewall-cmd --zone=public --add-port=8080/tcp --permanent
关闭指定端口	firewall-cmd --zone=public --remove-port=8080/tcp --permanent
立即生效(重新加载)	firewall-cmd --reload
查看开放端口	firewall-cmd --zone=public --list-ports

#### 配置MySQL
安装MySQL
1). 准备工作
在安装MySQL数据库之前，我们需要先检查一下当前Linux系统中，是否安装的有MySQL的相关服务（很多linux安装完毕之后，自带了低版本的mysql的依赖包），如果有，先需要卸载掉，然后再进行安装。

A. 通过rpm相关指令，来查询当前系统中是否存在已安装的mysql软件包，执行指令如下：
- rpm -qa                                  查询当前系统中安装的所有软件
- rpm -qa | grep mysql            查询当前系统中安装的名称带mysql的软件
- rpm -qa | grep mariadb        查询当前系统中安装的名称带mariadb的软件

通过rpm -qa 查询到系统通过rpm安装的所有软件，太多了，不方便查看，所以我们可以通过管道符 | 配合着grep进行过滤查询。
通过查询，我们发现在当前系统中存在mariadb数据库，是CentOS7中自带的，而这个数据库和MySQL数据库是冲突的，所以要想保证MySQL成功安装，需要卸载mariadb数据库。
RPM：全称为 Red-Hat Package Manager，RPM软件包管理器，是红帽Linux用于管理和安装软件的工具。

B. 通过 rpm 相关指令，来卸载对应的组件，执行指令如下：
在rpm中，卸载软件的语法为：rpm -e --nodeps  软件名称
那么，我们就可以通过指令，卸载 mariadb，具体指令为： rpm -e --nodeps mariadb-libs-5.5.60-1.el7_5.x86_64

我们看到执行完毕之后， 再次查询 mariadb，就查不到了，因为已经被成功卸载了。

2). 将资料中提供的MySQL安装包上传到Linux并解压
A. 上传MySQL安装包
在课程资料中，提供的有MySQL的安装包 ，我们需要将该安装包上传到Linux系统的根目录 /root 下面。

B. 解压到 当前目录
执行如下指令:
tar -xvf mysql-8.0.30-linux-glibc2.12-x86_64.tar.xz

C. 将解压后的文件夹移动到 /usr/local 目录下， 并改名为 mysql
mv mysql-8.0.30-linux-glibc2.12-x86_64 /usr/local/mysql

cd /usr/local/mysql

3). 配置系统环境变量
配置MySQL的环境变量, 通过vi编辑器编辑 /etc/profile 文件, 在尾部追加:
export MYSQL_HOME=/usr/local/mysql
export PATH=$MYSQL_HOME/bin:$PATH

并执行如下指令, 注册MySQL为系统服务:
cp /usr/local/mysql/support-files/mysql.server /etc/init.d/mysql
chkconfig --add mysql

**5). 初始化数据库**
#创建一个用户组, 组名就叫mysql
groupadd mysql

#创建一个系统用户 mysql, 并归属于用户组 mysql
useradd -r -g mysql -s /bin/false mysql
#初始化mysql
mysqld --initialize --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data
执行上述指令时, 会输入如下日志，在日志中就输出了MySQL中root用户的一个临时密码【记得复制出来，记录下来】：



3.3.2 启动MySQL
A. 启动MySQL服务
systemctl start mysql

B. 通过命令, 登录MySQL
#xxxxx 代表上述生成的root的临时密码
mysql -uroot -pxxxxx


3.3.3 配置MySQL
A. 修改root用户的密码
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '1234';

注意: 这个root账号仅仅能够在本机localhost上访问，我们在windows上是无法访问的。如果需要在window上或其他服务器上也能远程访问，需要创建一个账号，用于远程访问的。


**B. 创建账号, 并授权远程访问**
CREATE USER 'root'@'%' IDENTIFIED BY '1234';

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';

FLUSH PRIVILEGES;

我们已经开启了MySQL的远程访问的权限，为什么还是连接不上MySQL服务器呢？？ 这是因为Linux系统的防火墙，将我们的访问拦截了。

#### 安装Nginx
Nginx的安装包，从官方下载下来的是c语言的源码包，我们需要自己编译安装。具体操作步骤如下：
1). 安装Nginx运行时需要的依赖
yum install -y pcre pcre-devel zlib zlib-devel openssl openssl-devel
安装C语言的编译环境.
yum install gcc-c++

2). 上传Nginx的源码包

3). 解压源码包到当前目录
tar -zxvf nginx-1.20.2.tar.gz

4). 进入到解压目录后，执行指令
#进入解压目录
cd nginx-1.20.2

#执行命令配置, 生成Makefile文件
./configure --prefix=/usr/local/nginx

5). 执行命令进行编译和安装
#编译
make

#编译安装
make install

3.4.2 启动Nginx
进入到nginx安装目录/usr/local/nginx，启动nginx服务
cd /usr/local/nginx/
sbin/nginx

启动完毕之后，我们可以通过 ps 指令查询当前系统中的nginx进程，从而确认nginx是否启动 。查看进程
ps -ef | grep nginx 

然后，我们就可以打开浏览器，访问服务器上的nginx 。

#### 项目部署
前端部署nginx
1. 将页面资源文件夹下的打包好的静态资源，上传到nginx的html目录中
2. 配置nginx的配置文件，在conf/nginx.conf中配置反向代理服务器及路径重写规则
```text
server {
        listen       80;
        server_name  localhost;
        client_max_body_size 10M;
        location / {
            root   html;
            index  index.html index.htm;
        }
        location ^~ /api/ {
            rewrite ^/api/(.*)$ /$1 break;
            proxy_pass http://127.0.0.1:8080;
        }
}
```
3. 在nginx的安装目录中，执行sbin目录下的nginx命令启动nginx服务：sbin/nginx  或 sbin/nginx -s reload

### Docker
附录：Docker安装
   1 卸载旧版
   首先如果系统中已经存在旧的Docker，则先卸载：
   yum remove docker \
   docker-client \
   docker-client-latest \
   docker-common \
   docker-latest \
   docker-latest-logrotate \
   docker-logrotate \
   docker-engine \
   docker-selinux

2 配置Docker的yum库
首先要安装一个yum工具
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
安装成功后，执行命令，配置Docker的yum源（已更新为阿里云源）：
sudo yum-config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

sudo sed -i 's+download.docker.com+mirrors.aliyun.com/docker-ce+' /etc/yum.repos.d/docker-ce.repo
更新yum，建立缓存
sudo yum makecache fast

3 安装Docker
最后，执行命令，安装Docker
yum install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

4 启动和校验
```text
# 启动Docker
systemctl start docker

# 停止Docker
systemctl stop docker

# 重启
systemctl restart docker

# 设置开机自启
systemctl enable docker

# 执行docker ps命令，如果不报错，说明安装启动成功
docker ps
```


5 配置镜像加速
镜像地址可能会变更，如果失效可以百度找最新的docker镜像。
配置镜像步骤如下：
```text
# 创建目录
rm -f /etc/docker/daemon.json

# 复制内容
tee /etc/docker/daemon.json <<-'EOF'
{
"registry-mirrors": [
"http://hub-mirror.c.163.com",
"https://mirrors.tuna.tsinghua.edu.cn",
"http://mirrors.sohu.com",
"https://ustc-edu-cn.mirror.aliyuncs.com",
"https://ccr.ccs.tencentyun.com",
"https://docker.m.daocloud.io",
"https://docker.awsl9527.cn"
]
}
EOF

# 重新加载配置
systemctl daemon-reload

# 重启Docker
systemctl restart docker
```

1.2 命令解读
利用Docker快速的安装了MySQL，非常的方便，不过我们执行的命令到底是什么意思呢？
docker run -d \
--name mysql \
-p 3307:3306 \
-e TZ=Asia/Shanghai \
-e MYSQL_ROOT_PASSWORD=123 \
mysql:8
解读：
- docker run -d ：创建并运行一个容器，-d则是让容器以后台进程运行

- --name mysql  : 给容器起个名字叫mysql，你可以叫别的

- -p 3307:3306 : 设置端口映射。
   - 容器是隔离环境，外界不可访问。但是可以将宿主机端口映射容器内到端口，当访问宿主机指定端口时，就是在访问容器内的端口了。
   - 容器内端口往往是由容器内的进程决定，例如MySQL进程默认端口是3306，因此容器内端口一定是3306；而宿主机端口则可以任意指定，一般与容器内保持一致。
   - 格式： -p 宿主机端口:容器内端口，示例中就是将宿主机的3307映射到容器内的3306端口

- -e TZ=Asia/Shanghai : 配置容器内进程运行时的一些参数
   - 格式：-e KEY=VALUE，KEY和VALUE都由容器内进程决定
   - 案例中，TZ=Asia/Shanghai是设置时区；MYSQL_ROOT_PASSWORD=123是设置MySQL默认密码

- mysql:8 : 设置镜像名称，Docker会根据这个名字搜索并下载镜像
   - 格式：REPOSITORY:TAG，例如mysql:8.0，其中REPOSITORY可以理解为镜像名，TAG是版本号
   - 在未指定TAG的情况下，默认是最新版本，也就是mysql:latest

**命令介绍**
其中，比较常见的命令有：
docker pull 拉取镜像
docker push 推送镜像到DockerRegistry
docker images 查看本地镜像
docker rmi 删除本地镜像
docker run 创建并运行容器（不能重复创建）
docker stop 停止指定容器
docker start 启动指定容器
docker restart 重新启动容器
docker rm 删除指定容器
docker ps 查看容器
docker logs 查看容器运行日志
docker exec 进入容器
docker save 保存镜像到本地压缩文件
docker load 加载本地压缩文件到镜像
docker inspect 查看容器详细信息

**以Nginx为例给大家演示上述命令。**
```text
# 第1步，去DockerHub查看nginx镜像仓库及相关信息

# 第2步，拉取Nginx镜像 (比较耗时)
docker pull nginx:1.20.2

# 第3步，查看镜像
docker images

# 第4步，创建并允许Nginx容器
docker run -d --name nginx -p 80:80 nginx

# 第5步，查看运行中容器
docker ps

# 也可以加格式化方式访问，格式会更加清爽
docker ps --format "table {{.ID}}\t{{.Image}}\t{{.Ports}}\t{{.Status}}\t{{.Names}}"

# 第6步，访问网页，地址：http://虚拟机地址

# 第7步，停止容器
docker stop nginx

# 第8步，查看所有容器
docker ps -a --format "table {{.ID}}\t{{.Image}}\t{{.Ports}}\t{{.Status}}\t{{.Names}}"

# 第9步，再次启动nginx容器
docker start nginx

# 第10步，再次查看容器
docker ps --format "table {{.ID}}\t{{.Image}}\t{{.Ports}}\t{{.Status}}\t{{.Names}}"

# 第11步，查看容器详细信息
docker inspect nginx

# 第12步，进入容器,查看容器内目录
docker exec -it nginx bash

# 或者，可以进入MySQL
docker exec -it mysql mysql -uroot -p

# 第13步，删除容器
docker rm nginx

# 发现无法删除，因为容器运行中，强制删除容器
docker rm -f nginx
```
##### 数据卷挂载
介绍
数据卷（volume）是一个虚拟目录，是容器内目录与宿主机目录之间映射的桥梁。
以Nginx为例，我们知道Nginx中有两个关键的目录：
- html：放置一些静态资源
- conf：放置配置文件
  如果我们要让Nginx代理我们的静态资源，最好是放到html目录；如果我们要修改Nginx的配置，最好是找到conf下的nginx.conf文件。
  但遗憾的是，容器运行的Nginx所有的文件都在容器内部。所以我们必须利用数据卷将两个目录与宿主机目录关联，方便我们操作。

在上图中：
- 我们创建了两个数据卷：conf、html
- Nginx容器内部的conf目录和html目录分别与两个数据卷关联。
- 而数据卷conf和html分别指向了宿主机的/var/lib/docker/volumes/conf/_data目录和/var/lib/docker/volumes/html/_data目录

这样以来，容器内的conf和html目录就 与宿主机的conf和html目录关联起来，我们称为挂载。
此时，我们操作宿主机的/var/lib/docker/volumes/html/_data就是在操作容器内的/usr/share/nginx/html/_data目录。只要我们将静态资源放入宿主机对应目录，就可以被Nginx代理了。

小提示：
/var/lib/docker/volumes这个目录就是默认的存放所有容器数据卷的目录，其下再根据数据卷名称创建新目录，格式为/数据卷名/_data。

为什么不让容器目录直接指向宿主机目录呢？
- 因为直接指向宿主机目录就与宿主机强耦合了，如果切换了环境，宿主机目录就可能发生改变了。由于容器一旦创建，目录挂载就无法修改，这样容器就无法正常工作了。
- 但是容器指向数据卷，一个逻辑名称，而数据卷再指向宿主机目录，就不存在强耦合。如果宿主机目录发生改变，只要改变数据卷与宿主机目录之间的映射关系即可。

不过，我们通过由于数据卷目录比较深，不好寻找，通常我们也允许让容器直接与宿主机目录挂载而不使用数据卷，具体参考2.2.3小节。

2.2.2 命令
数据卷的相关命令有：
命令 说明 文档地址
docker volume create 创建数据卷
docker volume ls 查看所有数据卷 docs.docker.com
docker volume rm 删除指定数据卷
docker volume inspect 查看某个数据卷的详情
docker volume prune 清除数据卷 
注意：容器与数据卷的挂载要在创建容器时配置，对于创建好的容器，是不能设置数据卷的。而且创建容器的过程中，数据卷会自动创建。

**教学演示环节：演示一下nginx的html目录挂载**
```text
# 1.首先创建容器并指定数据卷，注意通过 -v 参数来指定数据卷
docker run -d --name nginx -p 80:80 -v html:/usr/share/nginx/html nginx:1.20.2

# 2.然后查看数据卷
docker volume ls


# 3.查看数据卷详情
docker volume inspect html


# 4.查看/var/lib/docker/volumes/html/_data目录
ll /var/lib/docker/volumes/html/_data


# 5.进入该目录，并随意修改index.html内容
cd /var/lib/docker/volumes/html/_data
vi index.html

# 6.打开页面，查看效果

# 7.进入容器内部，查看/usr/share/nginx/html目录内的文件是否变化
docker exec -it nginx bash
```

**教学演示环节：演示一下MySQL的匿名数据卷**
```text
# 1.查看MySQL容器详细信息
docker inspect mysql

# 关注其中.Config.Volumes部分和.Mounts部分
```
我们关注两部分内容，第一是.Config.Volumes部分：
```text
{
    "Config": {
    // ... 略
    "Volumes": {
    "/var/lib/mysql": {}
    }
    // ... 略
    }
}
```
可以发现这个容器声明了一个本地目录，需要挂载数据卷，但是数据卷未定义。这就是匿名卷。
然后，我们再看结果中的.Mounts部分：
```text
{
    "Mounts": [
        {
            "Type": "volume",
            "Name": "29524ff09715d3688eae3f99803a2796558dbd00ca584a25a4bbc193ca82459f",
            "Source": "/var/lib/docker/volumes/29524ff09715d3688eae3f99803a2796558dbd00ca584a25a4bbc193ca82459f/_data",
            "Destination": "/var/lib/mysql",
            "Driver": "local",
        }
    ]
}
```
可以发现，其中有几个关键属性：
- Name：数据卷名称。由于定义容器未设置容器名，这里的就是匿名卷自动生成的名字，一串hash值。
- Source：宿主机目录
- Destination : 容器内的目录
  上述配置是将容器内的/var/lib/mysql这个目录，与数据卷29524ff09715d3688eae3f99803a2796558dbd00ca584a25a4bbc193ca82459f挂载。于是在宿主机中就有了/var/lib/docker/volumes/29524ff09715d3688eae3f99803a2796558dbd00ca584a25a4bbc193ca82459f/_data这个目录。这就是匿名数据卷对应的目录，其使用方式与普通数据卷没有差别。

接下来，可以查看该目录下的MySQL的data文件：
ls -l /var/lib/docker/volumes/29524ff09715d3688eae3f99803a2796558dbd00ca584a25a4bbc193ca82459f/_data

注意：每一个不同的镜像，将来创建容器后内部有哪些目录可以挂载，可以参考DockerHub对应的页面 。


##### **挂载本地目录或文件**
可以发现，数据卷的目录结构较深，如果我们去操作数据卷目录会不太方便。在很多情况下，我们会直接将容器目录与宿主机指定目录挂载。挂载语法与数据卷类似：
```text
# 挂载本地目录
-v 本地目录:容器内目录

# 挂载本地文件
-v 本地文件:容器内文件
```
注意：本地目录或文件必须以 / 或 ./开头，如果直接以名字开头，会被识别为数据卷名而非本地目录名。

例如：
-v mysql:/var/lib/mysql # 会被识别为一个数据卷叫mysql，运行时会自动创建这个数据卷

-v ./mysql:/var/lib/mysql # 会被识别为当前目录下的mysql目录，运行时如果不存在会创建目录

教学演示，删除并重新创建mysql容器，并完成本地目录挂载：
- 挂载/root/mysql/data到容器内的/var/lib/mysql目录
- 挂载/root/mysql/init到容器内的/docker-entrypoint-initdb.d目录（初始化的SQL脚本目录）
- 挂载/root/mysql/conf到容器内的/etc/mysql/conf.d目录（这个是MySQL配置文件目录）
  在课前资料中已经准备好了mysql 的init目录、conf目录、data目录，可以直接将其上传到Linux服务器中的 /root/mysql 目录下。
  最终执行的指令如下：
```xml
  docker run -d \
  --name mysql \
  -p 3307:3306 \
  -e MYSQL_ROOT_PASSWORD=123 \
  -e TZ=Asia/Shanghai \
  -v /root/mysql/data:/var/lib/mysql \
  -v /root/mysql/init:/docker-entrypoint-initdb.d \
  -v /root/mysql/conf:/etc/mysql/conf.d \
  mysql:8
```


##### 自定义镜像
前面我们一直在使用别人准备好的镜像，那如果我要部署一个Java项目，把它打包为一个镜像该怎么做呢？ 那接下来，我们就来介绍一下如何自定义镜像。

2.3.1 镜像结构
要想自己构建镜像，必须先了解镜像的结构。
之前我们说过，镜像之所以能让我们快速跨操作系统部署应用而忽略其运行环境、配置，就是因为镜像中包含了程序运行需要的系统函数库、环境、配置、依赖。
因此，自定义镜像本质就是依次准备好程序运行的基础环境、依赖、应用本身、运行配置等文件，并且打包而成。

举个例子，我们要从0部署一个Java应用，大概流程是这样：
- 准备一个linux服务（CentOS或者Ubuntu均可）
- 安装并配置JDK
- 上传Jar包
- 运行jar包

那因此，我们打包镜像也是分成这么几步：
- 准备Linux运行环境（java项目并不需要完整的操作系统，仅仅是基础运行环境即可）
- 安装并配置JDK
- 拷贝jar包
- 配置启动脚本

上述步骤中的每一次操作其实都是在生产一些文件（系统运行环境、函数库、配置最终都是磁盘文件），所以镜像就是一堆文件的集合。
但需要注意的是，镜像文件不是随意堆放的，而是按照操作的步骤分层叠加而成，每一层形成的文件都会单独打包并标记一个唯一id，称为Layer（层）。这样，如果我们构建时用到的某些层其他人已经制作过，就可以直接拷贝使用这些层，而不用重复制作。

例如，第一步中需要的Linux运行环境，通用性就很强，所以Docker官方就制作了这样的只包含Linux运行环境的镜像。我们在制作java镜像时，就无需重复制作，直接使用Docker官方提供的CentOS或Ubuntu镜像作为基础镜像。然后再搭建其它层即可，这样逐层搭建，最终整个Java项目的镜像结构如图所示：

###### **Dockerfile**
由于制作镜像的过程中，需要逐层处理和打包，比较复杂，所以Docker就提供了自动打包镜像的功能。我们只需要将打包的过程，每一层要做的事情用固定的语法写下来，交给Docker去执行即可。而这种记录镜像结构的文件就称为Dockerfile。

其中的语法比较多，比较常用的有：
指令 说明 示例
FROM 指定基础镜像 FROM centos:7
ENV 设置环境变量，可在后面指令使用 ENV key value
COPY 拷贝本地文件到镜像的指定目录 COPY ./xx.jar /tmp/app.jar
RUN 执行Linux的shell命令，一般是安装过程的命令 RUN yum install gcc
EXPOSE 指定容器运行时监听的端口，是给镜像使用者看的 EXPOSE 8080
ENTRYPOINT 镜像中应用的启动命令，容器运行时调用 ENTRYPOINT java -jar xx.jar

例如，要基于 centos:7 镜像来构建一个Java应用，其Dockerfile内容如下：
```xml
# 使用 CentOS 7 作为基础镜像
FROM centos:7

# 添加 JDK 到镜像中
COPY jdk17.tar.gz /usr/local/
RUN tar -xzf /usr/local/jdk17.tar.gz -C /usr/local/ &&  rm /usr/local/jdk17.tar.gz

# 设置环境变量
ENV JAVA_HOME=/usr/local/jdk-17.0.10
ENV PATH=$JAVA_HOME/bin:$PATH

# 创建应用目录
RUN mkdir -p /app
WORKDIR /app

# 复制应用 JAR 文件到容器
COPY app.jar app.jar

# 暴露端口
EXPOSE 8080

# 运行命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
```

Dockerfile文件编写好了之后，就可以使用如下命令来构建镜像了。
docker build -t 镜像名 .
- -t ：是给镜像起名，格式依然是repository:tag的格式，不指定tag时，默认为latest
- .  ：是指定Dockerfile所在目录，如果就在当前目录，则指定为"."


##### **网络** 多个容器之间互相访问
上节课我们创建了一个Java项目的容器，而Java项目往往需要访问其它各种中间件，例如MySQL、Redis等。现在，我们的容器之间能否互相访问呢？我们来测试一下
首先，我们查看下MySQL容器的详细信息，重点关注其中的网络IP地址：
```xml
# 1.用基本命令，寻找Networks.bridge.IPAddress属性
docker inspect mysql

# 也可以使用format过滤结果
docker inspect --format='{{range .NetworkSettings.Networks}}{{println .IPAddress}}{{end}}' mysql

# 得到IP地址如下：
172.17.0.2

# 2.然后通过命令进入dd容器
docker exec -it dd bash

# 3.在容器内，通过ping命令测试网络
ping 172.17.0.2

# 结果
PING 172.17.0.2 (172.17.0.2) 56(84) bytes of data.
64 bytes from 172.17.0.2: icmp_seq=1 ttl=64 time=0.053 ms
64 bytes from 172.17.0.2: icmp_seq=2 ttl=64 time=0.059 ms
64 bytes from 172.17.0.2: icmp_seq=3 ttl=64 time=0.058 ms
```
发现可以互联，没有问题。

但是，容器的网络IP其实是一个虚拟的IP，其值并不固定与某一个容器绑定，如果我们在开发时写死某个IP，而在部署时很可能MySQL容器的IP会发生变化，连接会失败。

常见命令有：
命令 说明
docker network create 创建一个网络
docker network ls 查看所有网络
docker network rm 删除指定网络
docker network prune 清除未使用的网络
docker network connect 使指定容器连接加入某网络
docker network disconnect 使指定容器连接离开某网络
docker network inspect 查看网络详细信息

教学演示：自定义网络
```xml
# 1.首先通过命令创建一个网络
docker network create itheima

# 2.然后查看网络
docker network ls

# 结果：
NETWORK ID     NAME      DRIVER    SCOPE
639bc44d0a87   bridge    bridge    local
403f16ec62a2   itheima     bridge    local
0dc0f72a0fbb   host      host      local
cd8d3e8df47b   none      null      local
# 其中，除了itheima以外，其它都是默认的网络


# 3.让 myapp 和 mysql 都加入该网络
# 3.1.mysql容器，加入 itheima 网络
docker network connect itheima mysql

# 3.2.myapp容器，也就是我们的java项目, 加入 itheima 网络
docker network connect itheima myapp


# 4.进入dd容器，尝试利用别名访问db
# 4.1.进入容器
docker exec -it myapp bash

# 4.2.用容器名访问
ping mysql

# 结果：
PING mysql (172.18.0.2) 56(84) bytes of data.
64 bytes from mysql.itheima (172.18.0.2): icmp_seq=1 ttl=64 time=0.044 ms
64 bytes from mysql.itheima (172.18.0.2): icmp_seq=2 ttl=64 time=0.054 ms
```
OK，现在无需记住IP地址也可以实现容器互联了。


##### 项目部署
   3.1 部署服务端
- 需求：将我们开发的 tlias-web-management 项目打包为镜像，并部署。
- 步骤：
    1. 修改项目的配置文件，修改数据库服务地址（打包package）。
    2. 编写Dockerfile文件（AI辅助）。
    3. 构建Docker镜像，部署Docker容器，运行测试。

1). 修改项目的配置文件，修改数据库服务地址（打包package）。

然后，执行maven中的package生命周期，进行打包(跳过测试)，并将打包后的jar包命名为 tlias.jar 。

2). 编写Dockerfile文件。
文件名 Dockerfile:
```xml
# 使用 CentOS 7 作为基础镜像
FROM centos:7

# 添加 JDK 到镜像中
COPY jdk17.tar.gz /usr/local/
RUN tar -xzf /usr/local/jdk17.tar.gz -C /usr/local/ &&  rm /usr/local/jdk17.tar.gz

# 设置环境变量
ENV JAVA_HOME=/usr/local/jdk-17.0.10
ENV PATH=$JAVA_HOME/bin:$PATH

# 阿里云OSS环境变量
ENV OSS_ACCESS_KEY_ID=LTAI5tP6dc4cvccdvvySE39X
ENV OSS_ACCESS_KEY_SECRET=ZSyIT31qhxIkS0dH1H9WzHqPiyM3Ot

#统一编码
ENV LANG=en_US.UTF-8
ENV LANGUAGE=en_US:en
ENV LC_ALL=en_US.UTF-8

# 创建应用目录
RUN mkdir -p /tlias
WORKDIR /tlias

# 复制应用 JAR 文件到容器
COPY  tlias.jar  tlias.jar

# 暴露端口
EXPOSE 8080

# 运行命令
ENTRYPOINT ["java","-jar","/tlias/tlias.jar"]
```
由于项目要运行，需要依赖jdk的环境，所以这里我们需要将tlias.jar，jdk17.tar.gz，Dockerfile三个文件，上传到Linux服务器的 /root/tlias 目录下（如果没有这个目录，提前创建好）。


3). 构建Docker镜像，部署Docker容器，运行测试。
- 构建Docker镜像
  docker build -t tlias:1.0 .

- 部署Docker容器
  docker run -d --name tlias-server --network itheima -p 8080:8080  tlias:1.0
  --network  itheima ：将创建的容器，加入到itheima网络，就可以和itheima网络中的容器通信了。

通过 docker logs -f 容器名，就可以查看容器的运行日志。

这样后端服务，就已经启动起来了。

**部署前端**
- 需求：创建一个新的nginx容器，将资料中提供的前端项目的静态资源部署到nginx中。
- 步骤：
    - 在宿主机上准备静态文件及配置文件存放目录(在 /usr/local 目录下创建 tlias-web 目录)。
      -v /usr/local/tlias-web/html:/usr/share/nginx/html
      -v /usr/local/tlias-web/conf/nginx.conf:/etc/nginx/nginx.conf
    - 部署nginx容器

1). 部署nginx容器（设置目录映射）。
- 将资 资料/04. 项目部署/前端项目 中的 目录html和 配置文件存放目录 conf，上传至服务器端的 /usr/local/tlias-web目录下。

- 执行如下命令，部署nginx容器
  docker run -d \
  --name nginx-tlias \
  -v /usr/local/tlias-web/html:/usr/share/nginx/html \
  -v /usr/local/tlias-web/conf/nginx.conf:/etc/nginx/nginx.conf \
  --network itheima \
  -p 80:80 \
  nginx:1.20.2

前后端都部署完毕后，就可以打开浏览器，来测试一下。访问前端的nginx服务器 。


##### **DockerCompose**
大家可以看到，我们部署一个简单的java项目，其中包含3个容器：
- MySQL
- Nginx
- Java项目
  而稍微复杂的项目，其中还会有各种各样的其它中间件，需要部署的东西远不止3个。如果还像之前那样手动的逐一部署，就太麻烦了。

而Docker Compose就可以帮助我们实现多个相互关联的Docker容器的快速部署。它允许用户通过一个单独的 docker-compose.yml 模板文件（YAML 格式）来定义一组相关联的应用容器。

基本语法
docker-compose文件中可以定义多个相互关联的应用容器，每一个应用容器被称为一个服务（service）。由于service就是在定义某个应用的运行时参数，因此与docker run参数非常相似。

举例来说，用docker run部署MySQL的命令如下：
docker run -d \
--name nginx-tlias \
-p 80:80 \
-v /usr/local/app/html:/usr/share/nginx/html \
-v /usr/local/app/conf/nginx.conf:/etc/nginx/nginx.conf \  
--network itheima \
nginx:1.20.2

如果用docker-compose.yml文件来定义，就是这样：
```yaml
services:
  mysql:
    image: "nginx:1.20.2"
    container_name: nginx-tlias
    ports:
    - "80:80"
    volumes:
      - "/usr/local/app/html:/usr/share/nginx/html"
      - "/usr/local/app/conf/nginx.conf:/etc/nginx/nginx.conf"
    networks:
    - itheima
networks:
  itheima:
    name: itheima
```

对比如下：
**docker run参数** **docker compose指令** **说明**
--name container_name 容器名称
-p ports 端口映射
-e environment 环境变量
-v volumes 数据卷配置
--network networks 网络
明白了其中的对应关系，相信编写docker-compose文件应该难不倒大家。
```yaml
services:
  mysql:
    image: mysql:8
    container_name: mysql
    ports:
      - "3307:3306"
    environment:
      TZ: Asia/Shanghai
      MYSQL_ROOT_PASSWORD: 123
    volumes:
      - "/usr/local/app/mysql/conf:/etc/mysql/conf.d"
      - "/usr/local/app/mysql/data:/var/lib/mysql"
      - "/usr/local/app/mysql/init:/docker-entrypoint-initdb.d"
    networks:
      - tlias-net
  tlias:
    build: 
      context: .
      dockerfile: Dockerfile
    container_name: tlias-server
    ports:
      - "8080:8080"
    networks:
      - tlias-net
    depends_on:
      - mysql
  nginx:
    image: nginx:1.20.2
    container_name: nginx
    ports:
      - "80:80"
    volumes:
      - "/usr/local/app/nginx/conf/nginx.conf:/etc/nginx/nginx.conf"
      - "/usr/local/app/nginx/html:/usr/share/nginx/html"
    depends_on:
      - tlias
    networks:
      - tlias-net
networks:
  tlias-net:
    name: itheima
```

**基础命令**
编写好docker-compose.yml文件，就可以部署项目了。语法如下：
docker compose [OPTIONS] [COMMAND]
其中，OPTIONS和COMMAND都是可选参数，比较常见的有：
类型 参数或指令 说明
Options -f 指定compose文件的路径和名称

-p 指定project名称。project就是当前compose文件中设置的多个service的集合，是逻辑概念

Commands up 创建并启动所有service容器

down 停止并移除所有容器、网络

ps 列出所有启动的容器

logs 查看指定容器的日志

stop 停止容器

start 启动容器

restart 重启容器

top 查看运行的进程

exec 在指定的运行中容器中执行命令


**操作演示**
1). 在 Linux 服务器的 /usr/local 目录下创建目录 app，并切换到 /usr/local/app 目录。
2). 上传资料中提供的 "资料/05. Docker Compose" 中的文件及文件夹到 /usr/local/app 目录中，如下所示：

注意，资料中提供的Dockerfile文件中的阿里云OSS的 AccessKeyId，AccessKeySecret需要替换成自己的。
3). 执行如下指令，基于DockerCompose部署项目。
docker compose up -d


项目启动完毕之后，就可以启动服务器测试喽 。
