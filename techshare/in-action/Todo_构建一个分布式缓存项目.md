
## 分布式缓存项目

// todo

参考设计：

- http://www.proxool.cn/

```
（1）组件式架构设计。cachekit中的kit意为"组件"，整个系统分为：内存组件，磁盘组件，TCP组件等多个模块。
（2）设计精巧的序列化机制，利用内存索引实现磁盘数据的快速查找，与kafka有异曲同工之妙。
（3）消息队列机制，实现异步数据存储；线程池机制，实现Reactor设计理念
（4）服务注册、服务发现，让分布式可扩展变得异常灵活 
（5）面向消息开发思想，面向接口编程思想，让编程不在局限于面向对象开发思想。总体设计架构图如下所示：
```

![](http://www.proxool.cn/static/mstx/img/cachekit-struct.png)

- 深入分布式缓存：从原理到实践
