# 学生管理系统（Java版）
前言：这个是大二做的课设（还是学生管理系统...），理论上虽然是4个人一组一起做的，但是，注意这个“但是”，还是我一个人承担了所有...代码和文档基本都是我一个人写的，当初直接肝得吐血，这也是为啥后面的 Web 版部分功能没有完成的原因。

## 项目介绍
项目分为一个JavaSwing写的GUI桌面应用和一个半成品的Web应用，下图是项目的整体功能结构展示

![请添加图片描述](https://img-blog.csdnimg.cn/5ff043f0d72e4fbe8fcc2bf66f3e01f0.png)

### JavaSwing
JavaSwing这里使用的是 MyBatis + Spring 的框架组合，后面发现使用 Spring 框架在 Swing 开发的程序上好像是一个错误。
另外，JavaSwing 版里面可能还有一些逻辑上的未知 bug。 

#### 功能展示（部分）
1、登录模块

![在这里插入图片描述](https://img-blog.csdnimg.cn/9fd78e729bde479fb508754e209bb36c.png)

2、系统设置模块

![在这里插入图片描述](https://img-blog.csdnimg.cn/b066c1a4dcb64b28ae2e5510a797bfb1.png)

3、学生管理模块

学生添加

![在这里插入图片描述](https://img-blog.csdnimg.cn/0d09263e62634ecf8a584d23fc3c45f0.png)

学生列表

![在这里插入图片描述](https://img-blog.csdnimg.cn/ecd2698227a249cb95cb31088b0f20ea.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5bCP5YWz5ZCM5a2m5Zac5qyi5ZCD5rGJ5aCh,size_19,color_FFFFFF,t_70,g_se,x_16)


4、班级管理模块

班级添加

![在这里插入图片描述](https://img-blog.csdnimg.cn/84282205c6d2421e9a21b672467ebb28.png)

班级管理

![在这里插入图片描述](https://img-blog.csdnimg.cn/37b3d2ec964b4c6db89f8505dfeb943c.png)


5、成绩管理

成绩统计

![在这里插入图片描述](https://img-blog.csdnimg.cn/54b9636560d648209650fa9492ad2b35.png)

6、网页版
点击后可以跳转到浏览器的 http://localhost:8080 网址

![在这里插入图片描述](https://img-blog.csdnimg.cn/c26270205ae8406f935e7e5f98aff2ef.png)


#### 使用说明
使用 IDEA 打开项目，项目的结构如下图：

![在这里插入图片描述](https://img-blog.csdnimg.cn/4ddbb5d028774c19a44f8aae6314702d.png)

启动项目的话就运行 view 包里面的 LoginFrm 

![在这里插入图片描述](https://img-blog.csdnimg.cn/b7c0c38121e14374a5079136e71f3153.png)

#### 遇到的问题
##### 使用Spring进行依赖注入遇到的问题

Swing 是 Java 的一个进行 GUI 开发的包，在课设中我使用 Spring 对容器进行管理，但是在使用 Spring 注解进行容器依赖注入的时候出现了一个问题，依赖注入为 null，报错如下：
Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
	at com.view.LoginFrm.loginAct(LoginFrm.java:187)
	at com.view.LoginFrm$2.actionPerformed(LoginFrm.java:96)
在百度找了许久没找到问题所在，排除了 Spring 配置文件写错、或者是没加 @Service 注解等问题之后，我找到了网上的一个解释，如下：
在多线程时使用 @Autowired 总是获取不到 bean。

原因是：new thread 不在 Spring 容器中，也就无法获得 Spring 中的 bean 对象
JavaSwing 不是线程安全的，项目中一些地方是多线程运行的，许多 UI 线程在里面并发运行，所以在这些线程里面使用 Spring 注入失败，因为它们不是 Spring 管理的线程

而Spring在多线程的情况下是不允许使用注解注入依赖的，所以我们只能手动get到我们想要的bean对象，代码如下：

```java
private final ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
private final AdminService adminService = (AdminServiceImpl)context.getBean("AdminServiceImpl");
```
其实还可以通过配置线程池的方法管理，但是 Swing 我又不太熟，不知道它运行时有哪几个线程。

这个问题是我当初没想到的，如果我知道的话就不会用 Spring 在Swing 上面了...因为自己每个 view 层的类上都要手动获取依赖，比其实跟自己使用一个单例模式获取 MyBatis 的依赖没啥区别，使用了 Spring 反而更麻烦了一些

##### Mybatis使用HashMap作为结果集出现的问题

在编写查找指定签到方法的时候，我使用了List<HashMap<String,String>>作为返回值，但是它却显示错误，第一次是显示空指针错误，然后我配置了一下@Results结果集，如下：

```java
@Results({
@Result(property = "key",column = "attendance_num",jdbcType = JdbcType.INTEGER),
@Result(property = "value",column = "attendance_date",jdbcType = JdbcType.VARCHAR)})
```

但是它显示格式转换错误，于是我在控制台输出了一下查询结果，发现结果是这样的

[{value=2018-05-17, key=1}, {value=2018-04-17, key=1}, {value=2018-04-18, key=1}, {value=2018-04-19, key=3}, {value=2018-04-20, key=1}, {value=2018-04-21, key=1}, {value=2018-05-03, key=1}]

HashMap里面的值从JSON字符串的形式变成了xxx=xxx的形式，property属性值对应的是实体类的属性，但是HashMap里面的key和vlaue并不能算属性（是我想错了），所以此时Mybatis就会自己设置一个属性名，于是就变成了上面的结果。别问为啥不用xml文件的形式，当时时间不太够，就直接贪方便用注解了

解决方法：
将上面的结果值重新处理一下，再赋给HashMap

##### 删除带外键关联的数据时出现的问题

项目中Service层报错，如下：

```java
Cannot delete or update a parent row: a foreign key constraint fails (`ttms`.`s_attendance`, CONSTRAINT `student_attendance_foreign` FOREIGN KEY (`student_id`) REFERENCES `s_student` (`id`)); nested exception is java.sql.SQLIntegrityConstraintViolationException: Cannot delete or update a parent row: a foreign key constraint fails (`ttms`.`s_attendance`, CONSTRAINT `student_attendance_foreign` FOREIGN KEY (`student_id`) REFERENCES `s_student` (`id`))
```

查看了一下错误信息，问题出在Dao层，一条删除语句出现问题了，分析了一下原因，发现是设置了外键关联，这导致我们无法删除该条数据。

解决方法：
在删除数据前先设置外键无效，如下：

```sql
set foreign_key_checks = 0;
```

然后此时可以执行删除语句了
删除完之后再设置外键有效，如下：

```sql
set foreign_key_checks = 1;
```

这样就完美地删除了这条记录了。

### JavaWeb
JavaWeb 这里使用的是 SpringBoot + Spring Data JPA 的框架组合，页面是 Thymeleaf 进行数据展示的，页面上有一个统计页面使用了 ECharts 进行数据可视化。
前面说到 Web 是一个半成品，它已经实现了的功能主要有登录、退出登录、、修改密码、学生管理、班级管理、成绩管理的成绩统计这几个功能模块。另外，它用的数据库和 JavaSwing 版用的是同一个，所以它们之前的数据其实是互通的。
总结：尽管页面丑了点，但是它可以作为一个模板继续开发下去。

#### 功能展示（部分）

1、登录界面

![在这里插入图片描述](https://img-blog.csdnimg.cn/f74dddcb66e34e6aa1496351f6b91709.png)

2、学生管理

学生列表

![在这里插入图片描述](https://img-blog.csdnimg.cn/b582d3364e9c49d490dfd359c3807a6f.png)

学生添加

![在这里插入图片描述](https://img-blog.csdnimg.cn/ca42235fa4114498a34ef0143c7ecf28.png)


3、班级管理

班级列表

![在这里插入图片描述](https://img-blog.csdnimg.cn/6e4174e65dc4404b894a38b1a2296c21.png)

班级添加

![在这里插入图片描述](https://img-blog.csdnimg.cn/66124a53cf4a47b78965ead19aef5c0e.png)

4、成绩统计

![在这里插入图片描述](https://img-blog.csdnimg.cn/ced6869f448442aaa931b618843b08fe.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/c08d252b9d7a4b9eb76064bf0424afef.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/05ca008d46ef4e96a9c83ba34fcb6cff.png)

#### 使用说明
项目结构图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/f9eadc0a56a443488601f5c8759a6808.png)

启动的话直接进入 DemoApplication 类里面右键启动就可以了。

#### 遇到的问题
##### 使用JPA更新数据库时遇到的问题
使用 Spring Data JPA 做 Web 端的持久化层的内容时，遇到了一个错误，如下：

```java
Executing an update/delete query
```

在百度查找一番之后，发现是 JPA 如果执行 update 或 delete 等操作时，要在 Dao 或者 Service 层加上 @Transactiona l注解，代表这是一个事务级别的操作，这相当于 JPA 的一个使用规范吧，因为 JPA 要求，’没有事务支持，不能执行更新和删除操作’。
