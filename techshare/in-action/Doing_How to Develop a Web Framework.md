
## How to develop a web framework
 

项目地址：http://github.com/shniu/strawberry

项目 WIKI: http://github.com/shniu/strawberry/wiki

My Web Framework's Target:

- Easy Async Support
- Beautiful API
- Easy cluster
- Good Router


### IoC

使用 Java 开发 Web 框架前，我们需要先构思一个 IoC 容器的实现。在 Java 中，创建对象的方式有如下几种：

1. 直接使用 new，如 `new Object()`
2. 使用序列化和反序列化的方式，从文件中将对象通过反序列化的方式生成 java 内存中的对象
3. 使用对象的 clone 方法，可以将对象复制一份一样的对象出来
4. 使用反射机制，根据类的定义，这为动态加载类并实例化提供了可能性

可见，如果我们在构建复杂系统的时候，对象之间的依赖关系是很复杂的，如果通过直接 new 的方式会造成很严重的依赖关系，那么耦合也就很严重；如果某个接口的实现发生变化，所带来的影响面会很广。解决这个问题的办法就是，我们需要将创建对象，维护对象关系的事情交出来，不再由使用方管理，而是由框架来做，这样使用方专注做业务处理，框架来做对象的加载、创建、维护等管理工作，这里的对象我们一般称之为 Bean，管理的场所称之为 Bean容器或者 IoC 容器。

我们在做工程时，其实更偏向于做如下的选择：

- 低耦合，高内聚
- 组合
- 抽象
- 模块化
- 组件化
- ...

IoC 主要解决了解耦的问题，`借助于“第三方”实现具有依赖关系的对象之间的解耦`, 描述了控制权转移。

1. 由IOC容器在运行期间，动态地将某种依赖关系注入到对象之中
2. 获得依赖对象的过程被反转了
3. 通过引入IOC容器，利用依赖关系注入的方式，实现对象之间的解耦

**IOC Ref**

- [JSR 330](https://github.com/javax-inject/javax-inject)
- [Google Guice](https://github.com/google/guice/wiki/JSR330)
- [Java Dependency Injection – DI Design Pattern Example Tutorial](https://www.journaldev.com/2394/java-dependency-injection-design-pattern-example-tutorial)
- [浅谈IOC--说清楚IOC是什么](https://www.cnblogs.com/DebugLZQ/archive/2013/06/05/3107957.html)
- [深度理解依赖注入（Dependence Injection）](http://www.cnblogs.com/xingyukun/archive/2007/10/20/931331.html)

- [weld](http://weld.cdi-spec.org/)

这是一个 [CDI](http://cdi-spec.org/) 的实现

- [重读IOC：从DI到CDI](http://softlab.sdut.edu.cn/blog/subaochen/2013/06/%E9%87%8D%E8%AF%BBiocdi/)
- [Spring IOC 容器源码分析](http://www.importnew.com/27469.html)
- [The IoC container - Introduction to the Spring IoC container and beans](https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/beans.html)

## Ref framework

#### Java

- [Play web framework](https://www.playframework.com/)
- [xitrum](https://xitrum-framework.github.io/)
- [Sinetja](https://github.com/sinetja/sinetja)
- [GWT – Google Web Toolkit](http://www.gwtproject.org/)
- [Grails](https://grails.org/)
- [vertx.io](https://vertx.io/docs/)
- [Smart Framework](https://gitee.com/huangyong/smart-framework)
- [轻量级 Web 框架 Smart Framework 的实现](https://my.oschina.net/huangyong/blog/158546)


#### Golang

- [Go Web 编程](https://astaxie.gitbooks.io/build-web-application-with-golang/zh/)
- [Go Web 编程 English Version](https://astaxie.gitbooks.io/build-web-application-with-golang/en/)

关于 Go Web 编程的笔记记录

#### Python

- [Learning How to Build a Web Application](https://medium.com/@rchang/learning-how-to-build-a-web-application-c5499bd15c8f)
