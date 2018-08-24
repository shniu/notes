
# CAP 定理

> CAP定理是分布式系统中最基本的理论；
> In a distributed system (a collection of interconnected nodes that share data.), you can only have two out of the following three guarantees across a write/read pair: Consistency, Availability, and Partition Tolerance - one of them must be sacrificed.

## 含义

- Partition tolerance

叫分区容错。大多数分布式系统都分布在多个子网络。每个子网络就叫做一个区（partition）。分区容错的意思是，区间通信可能失败。比如，一台服务器放在中国，另一台服务器放在美国，这就是两个区，它们之间可能无法通信。

![Partition tolerance](https://www.wangbase.com/blogimg/asset/201807/bg2018071601.png)

上图中，G1 和 G2 是两台跨区的服务器。G1 向 G2 发送一条消息，G2 可能无法收到。系统设计的时候，必须考虑到这种情况。

一般来说，分区容错无法避免，因此可以认为 CAP 的 P 总是成立。

- Consistency

叫一致性。意思是，写操作之后的读操作，必须返回该值。举例来说，某条记录是 v0，用户向 G1 发起一个写操作，将其改为 v1。

![](https://www.wangbase.com/blogimg/asset/201807/bg2018071602.png)

接下来，用户的读操作就会得到 v1。这就叫一致性。

![](https://www.wangbase.com/blogimg/asset/201807/bg2018071603.png)

问题是，用户有可能向 G2 发起读操作，由于 G2 的值没有发生变化，因此返回的是 v0。G1 和 G2 读操作的结果不一致，这就不满足一致性了。

![](https://www.wangbase.com/blogimg/asset/201807/bg2018071604.png)

为了让 G2 也能变为 v1，就要在 G1 写操作的时候，让 G1 向 G2 发送一条消息，要求 G2 也改成 v1。

![](https://www.wangbase.com/blogimg/asset/201807/bg2018071605.png)

这样的话，用户向 G2 发起读操作，也能得到 v1。

- Availability

叫可用性。意思是只要收到用户的请求，服务器就必须给出回应。
用户可以选择向 G1 或 G2 发起读操作。不管是哪台服务器，只要收到请求，就必须告诉用户，到底是 v0 还是 v1，否则就不满足可用性。


## Ref

- [CAP Confusion: Problems with ‘partition tolerance’](http://blog.cloudera.com/blog/2010/04/cap-confusion-problems-with-partition-tolerance/)

