# wxwmvc

## 介绍
**wxwmvc** 是一个JavaEE项目快速开发脚手架，集成了最常用的框架,适用于`Restfull` 架构风格`Web Service`接口开发。项目使用Maven构建工具。

#### 组成
##### 主要框架
* **Spring**: 不多说，貌似JavaEE离不开他了。
* **Springmvc**: 与Spring无缝集成，简单灵活，支持Restful风格。
* **Mybatis** :个人认为比Hibernate好控制，毕竟是自己写的Sql语句。
* **Shiro**: Apache的权限管理框架，扩展性好，使用简单，个人认为比`Spring-Security`框架容易入手。
* **tomcat服务器** : 稳定，性能好，也可以换成jetty。
* **sf4j** ：支持多种日志系统，使用的是log4j。

##### 工具框架
* **Spring-Test** :包括了常用单元测试、集成测试、Web测试，`Src/Test/Java`下有几个简单的测试类。使用测试框架的好处就是节省时间，无需启动Server就能测试程序。
* **Mybatis-Pagehelper** :Mybatis的分页排序插件，由国人开发，用起来非常方便，[Mybatis-Pagehelperp] 项。
* **Mybatis通用Mapper3** 也是有上面作者开发，极其方便的使用Mybatis单表的增删改查，如果是单表操作，基本不用写Mapper文件,[Mybatis通用Mapper3]。
* **Spring-Mail**： 可修改`/src/main/resource/mail-config.properties`配置文件，这个配置文件配置的是主邮箱。
* **commons fileupload**:`spring mvc`中集成了`appache-commons-fileupload`上传组件。上传处理更便捷。


#### 开发工具
##### IDE
本项目默认以用`Eclipse`，也可以用[Intellij Idea]
##### 依赖管理工具
本项目默认使用`Maven`容易上手，也可以尝试`Gradle`

## 使用
### 下载
`Download Zip`或者`git clone`
``` shell
	git clone https://github.com/wxweven/wxwmvc.git
```


### 快速开始
新建或者配置一个mysql数据库，根据数据库信息修改`src/main/resources/jdbc.properties`文件。
修改mysql连接相关属性(mysql配置),然后进入命令行:



```

### 导入
使用Eclipse或IDEA导入

### mybatis-generator

#### 利用[mybatis-generator(MBG)] 生成`model/mapper/mapper.xml`文件
Mybatis考虑到手写XML文件的繁琐，因此开发了MBG工具，通用Mapper这个项目再次简化了mybatis的生成代码数量。
运行maven mybatis-generator:generate 生成，
具体配置参见 `src/test/resources/mybatis-generator/generatorConfig.xml`文件
