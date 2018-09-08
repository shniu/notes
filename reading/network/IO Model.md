
## I/O Model

在 `Unix 网络编程` 中介绍了5种 I/O Models：

- 阻塞 I/O
- 非阻塞 I/O
- I/O 多路复用
- 信号驱动的 I/O
- 异步 I/O

我们需要先了解一下 [C10K 问题](https://github.com/shniu/notes/blob/master/reading/network/C10K.md).

### C10K & C10M

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
