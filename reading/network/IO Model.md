
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

### I/O 多路复用

I / O 多路复用就是通过一种机制，一个进程可以监视多个描述符，一旦某个描述符就绪（一般是读就绪或者写就绪），能够通知程序进行相应的读写操作。但select，pselect，poll，epoll本质上都是同步I/O, 因为他们都需要在读写事件就绪后自己负责进行读写，也就是说这个读写过程是阻塞的.

## Ref

- [Thousands of Threads and Blocking I/O](https://www.slideshare.net/e456/tyma-paulmultithreaded1)

PPT 介绍了各种 I/O 模型和比较，很好的入门资料。
