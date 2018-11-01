
## Java summary

对 Java 做一个全方位的总结，我觉得第一个不得不去认真思考的问题是对 Java 平台的理解，也就是我们需要刨根问底 Java 它到底是一个什么样的东西？拨开 Java 的各种表象后，它的本质是什么样子的？

### 语言本质的思考

首先 Java 作为一门编程语言，他首先是编程语言，编程语言的本质是什么？是第一个思考的问题。之前有看过<<计算机程序的构造和解释>>一书, 每种语言都会提供如下几种机制：

1. 基本的表达形式
2. 组合的方法
3. 抽象的方法

Java 语言也不例外。Java 为我们提供了一个强大的生态，从基础的 Java SE 到构建强大服务的 Java EE，再围绕 Java 构建的一个浑厚无比的开源生态圈，足矣体现它的强大。

Java 语言的基本语法特性和编程方式，为我们提供了基本的表达形式，为我们提供了解决基本问题的方式；面向对象的思想为我们提供了抽象的方式，这是 Java 面对现实问题和现实世界的哲学。同时，Java 又提供了泛型编程、函数式编程的可能。

(待续...)

### Java 基础

// todo

- 基本数据类型
- [equals and ==](http://www.importnew.com/6804.html)

`==` 和 `equals` 是 Java 中比较相等的两种方式：

```
// ==
1. == 用来比较基本数据类型之间的值是否相等，如short, int, long, char, float, double, byte, boolean
2. == 也可以比较引用，在比较的时候判断的是他们的内存地址是否相同，如果内存地址不同，则返回 false

// equals
1. equals 是 Object 中的一个方法，最原始的实现是比较两个对象之间的内存地址是否相等，使用的是 `==`
2. Java 中所有的对象都继承自 Objcet, 所以其他对象都是可以 Override equals 的，基于业务需求重写equals方法是最好的实践之一，无论什么时候你重写了equals方法，你同样要重写hashcode()方法
3. String 重写 equals 和 HashCode 是一个非常好的例子，String 之间比较相等要使用 equals
```

- String 在 Java 中的实现

- Object 对象探索
- Java 的四种引用及使用场景
- HashCode
- Java 的异常体系
- Java 的多态实现
- Java 的泛型
- ...

**Java 基础 Ref**

- [Java SE Notes](https://github.com/francistao/LearningNotes/blob/master/Part2/JavaSE/Java%E5%9F%BA%E7%A1%80%E7%9F%A5%E8%AF%86.md)

### Java 语言设计思想

// todo

### Java 反射

> Reflection enables Java code to discover information about the fields, methods and constructors of loaded classes, and to use reflected fields, methods, and constructors to operate on their underlying counterparts, within security restrictions.
The API accommodates applications that need access to either the public members of a target object (based on its runtime class) or the members declared by a given class. It also allows programs to suppress default reflective access control.

反射的核心是 JVM 在运行时才动态加载类或调用方法/访问属性，它不需要事先（写代码的时候或编译期）知道运行对象是谁。

**Java Reflection**:

- [深入解析Java反射（1） - 基础](https://www.sczyh30.com/posts/Java/java-reflection-1/)
- [深入解析Java反射（2） - invoke方法](https://www.sczyh30.com/posts/Java/java-reflection-2/)
- [using java reflection](https://www.oracle.com/technetwork/articles/java/javareflection-1536171.html)

### Java 的类加载和双亲委托

主要去理解如下问题：

- 类加载器的作用，为什么需要它？
- 类加载的过程？
- 什么是双亲委托，为什么需要它？
- 如何自定义类加载器？什么时候需要自定义？

### JVM 内存模型


```
// 1. Java 的类加载机制和双亲委托模型
// 2. Java 的内存模型
// 3. 深入了解一下 JVM 和字节码
// 4. Java 并发
// 5. Java 的网络编程及 NIO
// 6. 一点点关于 Netty 的 IO 模型和线程调度模型
// 7. Java 的异常体系
// 8. Java 中的各种对象引用
// 9. Java 中的动态代理
// 10. Java 中的集合操作
// 11. Java 中的接口和抽象编程
// 12. Java 中的设计模式，Ref: http://blog.didispace.com/spring-design-partern/
```

### Servlet

**Ref of servlet**

- [Servlet 工作原理解析](https://www.ibm.com/developerworks/cn/java/j-lo-servlet/index.html)
- [Tomcat 系统架构与设计模式](https://www.ibm.com/developerworks/cn/java/j-lo-tomcat1/index.html)
- [设计模式分析](https://www.ibm.com/developerworks/cn/java/j-lo-tomcat2/)
- Servlet 规范

### 面试相关

- [Vector vs. ArrayList](https://www.geeksforgeeks.org/vector-vs-arraylist-java/)

1. Synchronization, Vector 是 synchronized，ArrayList 不是 synchronized
2. 性能，ArrayList 更快，因为不需要加锁
3. 数据扩容与缩容，ArrayList增长是50%，Vector是100%

## Ref

- [IBM Java 频道](https://www.ibm.com/developerworks/cn/java/)
- [Java programming language](https://howtodoinjava.com/java/basics/what-is-java-programming-language/)
- [Java 9](https://howtodoinjava.com/java9/java9-new-features-enhancements/)
- [Java 10](https://howtodoinjava.com/java10/java10-features/)
- [how to do in java](https://howtodoinjava.com/)
