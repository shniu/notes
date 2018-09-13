
在编写并发程序的时候，如何正确有效保护共享数据一直都是一个要解决的问题，而基本的解决手段就是同步，也就是说在遇到多个线程（或进程）竞争共享数据的时候，使用同步原子性操作进行串行，从而保证对数据的更改是可控的、正确的、有效的。同步又有阻塞同步和非阻塞同步两类。

我们在编写多线程程序的过程中，第一个需要保证的就是线程安全，所谓线程安全就是在多线程环境下，可以正确有效的处理共享数据、可修改的状态；如果多线程之间不存在竞争条件，那就不存在线程安全的问题。往往我们使用最多也是最具有暴力美学的就是同步块或者同步方法，比如 Java 中的 `synchronized`，C++ 中的 `mutex` 等。

从同步的类型来看，我们先从Blocking Synchronization说起，然后再到Non-Blocking Synchronization。

> 题外话：对于这些东西的理解需要一定的基础，如进程和线程模型，多进程和多线程等

## Blocking Synchronization

即阻塞同步方式。

在多线程中面临的主要问题：线程安全性、活跃性问题如死锁，活锁，饥饿等、性能问题如频繁的上下文切换，同步机制保护共享资源等；所以这些都给并发编程带来的挑战。


### Lock free

针对 lock free 要弄明白以下问题：

- 有哪些锁类型?



- 为什么需要 lock free ?

锁对于性能还是有很大影响的，它强制将并发执行的多个线程串行化，当然了是需要访问或者修改共享数据的地方做串行化处理。

- 和 lock free 相比，比较一下其他机制，如mutex, lock, lockless, non-blocking etc, 和并发编程有什么关系
- lock free 适用的场景
- lock free 的关键技术和解决问题的方式
- lock free 的实践，分别研究一下在 Java/ C++ / Golang 中的 lock free 实现

### 先来回顾一下并发编程的东西

在多线程中，

- 线程安全

### Lock free data structures 

> Lock-free data structures guarantee the progress of at least one thread when executing mutlithreaded procedures, thereby helping you avoid deadlock. 

Lock Free 都是在有特定需求的场景中使用的设计，不会在一个应用中需要用到锁的地方都使用。当我们准备要满足 Lock-Free 编程中的非阻塞条件时，有一系列的技术和方法可供使用，如原子操作（Atomic Operations）、内存栅栏（Memory Barrier）、避免 ABA 问题（Avoiding ABA Problem）等

![lock free](https://images0.cnblogs.com/blog/175043/201410/231028206521362.png)

- 原子操作

原子操作（Atomic Operations）在操作内存时可以被看做是不可分割的（Indivisible），其他线程不会打断该操作，没有操作只被完成一部分之说。

## 总结

虽然这篇文章的标题是关于lock free的，但我们从并发编程的角度出发，从简单到复杂的方式分析了有锁到无锁的过程，以及每种锁的适用场景；此外，一个不变的准则是我们需要根据处理问题的不同来考虑使用何种锁解决问题，即要考虑到问题复杂度，也需要考虑实现复杂度，又要兼顾到性能要求。其实这也算是一片对并发编程的总结。

## Lock Free 相关的资料

- [Lock-Free Data Structures](https://erdani.com/publications/cuj-2004-10.pdf)
- [Lock-Free 编程](https://www.cnblogs.com/gaochundong/p/lock_free_programming.html)
- [透过 Linux 内核看无锁编程](https://www.ibm.com/developerworks/cn/linux/l-cn-lockfree/)
- [Is Parallel Programming Hard, And, If So, What Can You Do About It?](https://mirrors.edge.kernel.org/pub/linux/kernel/people/paulmck/perfbook/perfbook.2017.11.22a.pdf)

