
## I/O Model

在 `Unix 网络编程` 中介绍了5种 I/O Models：

- 阻塞 I/O
- 非阻塞 I/O
- I/O 多路复用
- 信号驱动的 I/O
- 异步 I/O

一些重要的概念：

```
1. IO模型中的同步、异步、阻塞和非阻塞
2. 5 种 IO 模型的工作机制：阻塞IO 非阻塞IO IO多路复用 信号驱动IO 异步IO
3. 操作系统的内核态和用户态, 进程切换, 线程切换, 进程的阻塞, fd, 缓存IO
4. C10K 和 C10M 问题该如何解决
5. 重点再来聊一下异步IO
6. 常用的IO模型的实现及类库
```

### 同步、异步、阻塞和非阻塞

- 同步和异步仅仅是**关注的消息如何通知的机制**，同步的情况下，是由处理消息者自己去等待消息是否被触发，而异步的情况下是由触发机制来通知处理消息者
- 阻塞与非阻塞关注的是**等待消息通知时的状态**

可参考 [聊聊同步、异步、阻塞与非阻塞](https://www.jianshu.com/p/aed6067eeac9)

### 内核态和用户态

操作系统的核心是内核，独立于普通的应用程序，可以访问受保护的内存空间，也有访问底层硬件设备的所有权限. 为了保证用户进程不能直接操作内核（kernel），保证内核的安全，操作系统将虚拟空间划分为两部分，一部分为内核空间，一部分为用户空间. **可见，两种状态的划分是为了安全性方面的考虑，OS 提供对低层空间的操作全力并对外开放安全接口，由用户进程进行调用来完成模型操作。** 对 Linux 系统而言，将最高的1G字节（从虚拟地址0xC0000000到0xFFFFFFFF），供内核使用，称为内核空间，而将较低的3G字节（从虚拟地址0x00000000到0xBFFFFFFF），供各个进程使用，称为用户空间。

### 进程切换

为了控制进程的执行，内核必须有能力挂起正在CPU上运行的进程，并恢复以前挂起的某个进程的执行，这也是多进程多任务的基本要求。

```
// 进程切换大致要做的事情

保存处理机上下文，包括程序计数器和其他寄存器。
更新PCB信息。
把进程的PCB移入相应的队列，如就绪、在某事件阻塞等队列。
选择另一个进程执行，并更新其PCB。
更新内存管理的数据结构。
恢复处理机上下文。
```

有关 [进程切换](http://guojing.me/linux-kernel-architecture/posts/process-switch/) 的消息说明

### 进程阻塞

正在执行的进程，由于期待的某些事件未发生，如请求系统资源失败、等待某种操作的完成、新数据尚未到达或无新工作做等，则由系统自动执行阻塞原语(Block)，使自己由运行状态变为阻塞状态。可见，进程的阻塞是进程自身的一种主动行为，也因此只有处于运行态的进程（获得CPU），才可能将其转为阻塞状态。当进程进入阻塞状态，是不占用CPU资源的。

### fd

文件描述符fd是一个用于表述指向文件的引用的抽象化概念，文件描述符在形式上是一个非负整数。它是一个索引值，指向内核为每一个进程所维护的该进程打开文件的记录表。当程序打开一个现有文件或者创建一个新文件时，内核向进程返回一个文件描述符。在程序设计中，一些涉及底层的程序编写往往会围绕着文件描述符展开。但是文件描述符这一概念往往只适用于UNIX、Linux这样的操作系统。

### 缓存 IO

缓存 IO 又被称作标准 IO，大多数文件系统的默认 IO 操作都是缓存 IO。在 Linux 的缓存 IO 机制中，操作系统会将 IO 的数据缓存在文件系统的页缓存（ page cache ）中，也就是说，**数据会先被拷贝到操作系统内核的缓冲区中，然后才会从操作系统内核的缓冲区拷贝到应用程序的地址空间。**

数据在传输过程中需要在应用程序地址空间和内核进行多次数据拷贝操作，这些数据拷贝操作所带来的 CPU 以及内存开销是非常大的。

### 几种 IO 模型的简单说明

网络IO的本质是socket的读取，socket在linux系统被抽象为流，IO可以理解为对流的操作。

对 Socket 流而言，需要处理如下两件事情：

```
- 第一步：通常涉及等待网络上的数据分组到达，然后被复制到内核的某个缓冲区
- 第二步：把数据从内核缓冲区复制到应用进程缓冲区
```

[聊聊Linux 五种IO模型](https://www.jianshu.com/p/486b0965c296), 讲的比较清楚了

#### IO 多路复用

I / O 多路复用就是通过一种机制，一个进程可以监视多个描述符，一旦某个描述符就绪（一般是读就绪或者写就绪），能够通知程序进行相应的读写操作。但select，pselect，poll，epoll本质上都是同步I/O, 因为他们都需要在读写事件就绪后自己负责进行读写，也就是说这个读写过程是阻塞的.

- [IO多路复用原理剖析](https://juejin.im/post/59f9c6d66fb9a0450e75713f), 这篇文章 【todo】

### C10K & C10M

我们需要先了解一下 [C10K 问题](https://github.com/shniu/notes/blob/master/reading/network/C10K.md).

##### C10K

The C10k problem is the problem of optimising network sockets to handle a large number of clients at the same time.

- [The c10k problem](http://www.kegel.com/c10k.html)

总结了很多关于 C10K 相关的问题，和解决方法。

- [C10K wiki](https://en.wikipedia.org/wiki/C10k_problem)


##### 关于 [C10M](https://mrotaru.wordpress.com/2015/05/20/how-migratorydata-solved-the-c10m-problem-10-million-concurrent-connections-on-a-single-commodity-server/)

The C10M problem relates to the classic C10K Internet scalability problem, which originally occurred in the context of Web servers.

- [Scaling to 12 Million Concurrent Connections: How MigratoryData Did It](https://mrotaru.wordpress.com/2013/10/10/scaling-to-12-million-concurrent-connections-how-migratorydata-did-it/)

- [Fast C10M: MigratoryData running on Zing JVM achieves near 1 Gbps messaging to 10 million concurrent users with 15 millisecond consistent latency
](https://mrotaru.wordpress.com/2016/01/20/migratorydata-makes-its-c10m-scalability-record-more-robust-with-zing-jvm-achieve-near-1-gbps-messaging-to-10-million-concurrent-users-with-only-15-milliseconds-consistent-latency/)


## Ref

- [Thousands of Threads and Blocking I/O](https://www.slideshare.net/e456/tyma-paulmultithreaded1)

PPT 介绍了各种 I/O 模型和比较，很好的入门资料。
