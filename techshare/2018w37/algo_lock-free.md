
## Lock free

针对 lock free 要弄明白以下问题：

- 为什么需要 lock free ?
- 和 lock free 相比，比较一下其他机制，如mutex, lock, lockless, non-blocking etc, 和并发编程有什么关系
- lock free 适用的场景
- lock free 的关键技术和解决问题的方式
- lock free 的实践，分别研究一下在 Java/ C++ / Golang 中的 lock free 实现

### 先来回顾一下并发编程的东西

在多线程中，

- 线程安全

### Lock free data structures 

> Lock-free data structures guarantee the progress of at least one thread when executing mutlithreaded procedures, thereby helping you avoid deadlock. 


## Lock Free 相关的资料

- [Lock-Free 编程](https://www.cnblogs.com/gaochundong/p/lock_free_programming.html)

