
#### TODO
- [ ] spinning lock
- [ ] semaphone
- [ ] deadlock / [livelock](https://en.wikipedia.org/wiki/Deadlock#Livelock)
- [ ] 重量级锁 / 轻量级锁 / 偏斜锁 等
- [ ]  memory barriers

在编写并发程序的时候，如何正确有效保护共享数据一直都是一个要解决的问题，而基本的解决手段就是同步，也就是说在遇到多个线程（或进程）竞争共享数据的时候，使用同步原子性操作进行串行，从而保证对数据的更改是可控的、正确的、有效的。同步又有阻塞同步和非阻塞同步两类。

我们在编写多线程程序的过程中，第一个需要保证的就是线程安全，所谓线程安全就是在多线程环境下，可以正确有效的处理共享数据、可修改的状态；如果多线程之间不存在竞争条件，那就不存在线程安全的问题。往往我们使用最多也是最具有暴力美学的就是同步块或者同步方法，比如 Java 中的 `synchronized`，C++ 中的 `mutex` 等。

从同步的类型来看，我们先从Blocking Synchronization说起，然后再到Non-Blocking Synchronization。

> 题外话：对于这些东西的理解需要一定的基础，如进程和线程模型，多进程和多线程等

## Blocking Synchronization

即阻塞同步方式。基本意思就是在多线程工作环境下，线程到达临界区（也就是资源需要竞争的地方）时就会尝试去获取锁，然而这个锁已经被某个线程占用，所以自己就获取不到锁资源，就把自己的线程运行状态编程阻塞态，等待获取访问共享数据的锁。系统中常见的同步源于有 `mutex`, `semaphore `, `critical sections` 等。

- 在多线程中面临的主要问题

线程安全性、活跃性问题如死锁，活锁，饥饿等、性能问题如频繁的上下文切换，同步机制保护共享资源等；所以这些都给并发编程带来的挑战。

- 有哪些锁类型

在同步阻塞中，一般都是通过锁的方式进行同步访问的，基本都有哪些所类型呢？

```
锁类型：
1. 互斥锁 mutex or 信号量
2. 重入锁 
3. 读写锁
4. 自旋锁 spinning lock
```

## [Non-Blocking Synchronization](https://en.wikipedia.org/wiki/Non-blocking_algorithm)

当前线程无法获取锁的时候，并不会被阻塞，而是原地等待，直到获取锁，这期间并不会发生线程的上下文切换。比较流行的非阻塞同步算法有：

- Wait free
- Lock free
- Obstruction free

但是由于现实的各种原因，Obstruction-free 是 Non-blocking synchronization 中性能最差的，而 Wait-free 性能是最好的，但实现难度也是最大的，因此 Lock-free 是很多人的选择。

### Lock free

> 和 lock free 相比，比较一下其他机制，如mutex, lock, lockless, non-blocking etc, 和并发编程有什么关系

Lock free 是指能够确保执行它的所有线程中至少有一个能够继续往下执行。

- 为什么需要 lock free ?

之所以要引入 lock free，就要去了解一下并发编程下的锁机制，阻塞同步下的锁机制虽然可以提高一定的性能又能实现多线程的安全性、可见性等特点，但也会有一些问题存在，如锁机制会引入线程阻塞，增加线程的上下文切换，会带来一定的系统开销；锁机制在使用不当的情况下会引发死锁，饥饿等现象；同时锁的释放和获取也会带来一定的资源消耗。so，lock free 就是要在保证线程安全性、可见性的基础之上，来尽量解决这些问题。

- lock free 适用的场景
- lock free 的关键技术和解决问题的方式

对于 lock free 的实现，不得不说的就是对内存的原子操作，简单来说就是在操作内存时可以被看做是不可分割的（Indivisible），其他线程不会打断该操作，没有操作只被完成一部分之说。现在的 CPU 指令中就有很多原子性操作支持。

Read Modify Write 使得当有多个写入者想对相同的内存进行修改时，保证一次只执行一个操作。而 RMW 在不同的 CPU 上有不同的实现支持：

1. x86/64 和 Itanium 架构通过 Compare-And-Swap (CAS) 方式来实现；
2. PowerPC、MIPS 和 ARM 架构通过 Load-Link/Store-Conditional (LL/SC) 方式来实现；

比如在 x86 架构中使用 CMPXCHG 指令来实现 CAS 操作。

```
// CAS 的汇编级支持
__asm {
 mov edx, dest
 mov ecx, exchange_value
 mov eax, compare_value
 LOCK_IF_MP(mp)                 //判断是否是多核，是则添加LOCK指令维护顺序一致性
 cmpxchg dword ptr [edx], ecx
}
```

插播一下，什么是 CAS？[CAS](https://en.wikipedia.org/wiki/Compare-and-swap) 是在多线程下获取同步的一个原子性操作，它首先来比较一下内存上的值还是不是之前取的值，如果是就把新值替换掉旧值，如果不一致，就返回一个错误。

CAS 在原子性上使用 CPU 的指令得到了保证，Lock free 如何保证呢？比较流行的实现方式是基于 Cas-Loops, 方式如下：

```
通过 CAS Loop 来实现事务的 RMW 操作
1. 从指定的内存位置读取原始的值；
2. 根据读取到的原始的值计算出新的值；
3. 检测如果内存位置仍然是原始的值时，则将新值写入该内存位置；

// e.g.
void LockFreeStack::Push(Node* newHead)
{
  for (;;)
  {
    // Read the original value from a memory location.
    // Copy a shared variable (m_Head) to a local.
    Node* oldHead = m_Head;

    // Compute the new value to be set.
    // Do some speculative work, not yet visible to other threads.
    newHead->next = oldHead;

    // Set the new value only if the memory location is still the original value.
    // Next, attempt to publish our changes to the shared variable.
    // If the shared variable hasn't changed, the CAS succeeds and we return.
    // Otherwise, repeat.
    if (_InterlockedCompareExchange(&m_Head, newHead, oldHead) == oldHead)
      return;
  }
}
```

- CAS 容易引发的问题

ABA 问题是 CAS 有可能引发的问题，一般解决的办法是使用 double-length CAS，就是加上一个 counter, 每当对值做了修改就对 counter +1; ABA 问题其实和“掉包”是一样的，在系统中可能会引发一些问题。

### Lock free data structures 

> Lock-free data structures guarantee the progress of at least one thread when executing mutlithreaded procedures, thereby helping you avoid deadlock. 

Lock Free 都是在有特定需求的场景中使用的设计，不会在一个应用中需要用到锁的地方都使用。当我们准备要满足 Lock-Free 编程中的非阻塞条件时，有一系列的技术和方法可供使用，如原子操作（Atomic Operations）、内存栅栏（Memory Barrier）、避免 ABA 问题（Avoiding ABA Problem）等

![lock free](https://images0.cnblogs.com/blog/175043/201410/231028206521362.png)

### lock free 在不同编程语言中的实现

> lock free 的实践，分别研究一下在 Java/ C++ / Golang 中的 lock free 实现

#### Java

在 Java 中提供了一个 [concurrent atomic](https://docs.oracle.com/javase/10/docs/api/java/util/concurrent/atomic/package-summary.html) 的 package，里面的实现有 `AtomicInteger`, `AtomicLong` 等很多原子性操作，他的底层实现是基于 CAS Loop 的方式做的。

#### C++

[Boost.lockfree](https://www.boost.org/doc/libs/1_60_0/doc/html/lockfree.html) 是 C++ 中 lock free 的实现

### 实例

### lock free queue

// todo

### lock free stack

// todo

## 总结

虽然这篇文章的标题是关于lock free的，但我们从并发编程的角度出发，从简单到复杂的方式分析了有锁到无锁的过程，以及每种锁的适用场景；此外，一个不变的准则是我们需要根据处理问题的不同来考虑使用何种锁解决问题，即要考虑到问题复杂度，也需要考虑实现复杂度，又要兼顾到性能要求。其实这也算是一片对并发编程的总结。

## Lock Free 相关的资料

- [awesome lock free](https://github.com/rigtorp/awesome-lockfree)

A collection of resources on wait-free and lock-free programming

- [Lock-Free Data Structures](https://erdani.com/publications/cuj-2004-10.pdf)
- [Lock-Free 编程](https://www.cnblogs.com/gaochundong/p/lock_free_programming.html)
- [透过 Linux 内核看无锁编程](https://www.ibm.com/developerworks/cn/linux/l-cn-lockfree/)
- [Is Parallel Programming Hard, And, If So, What Can You Do About It?](https://mirrors.edge.kernel.org/pub/linux/kernel/people/paulmck/perfbook/perfbook.2017.11.22a.pdf)

- [concurrencyfreaks lock free](https://concurrencyfreaks.blogspot.com/search?q=lock+free)
- [ConcurrentStack 的实现](https://referencesource.microsoft.com/#mscorlib/system/Collections/Concurrent/ConcurrentStack.cs)

实现基于 CAS Loop， allocate a new node on every push. This avoids having to worry about potential ABA issues, since the CLR GC ensures that a memory address cannot be reused before all references to it have died.
