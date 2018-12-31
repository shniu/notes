
大约在 6 年前我在一个非常有趣的时期加入 Linkedin. 那时我们正遇到单体架构、中心化数据库带来的限制，需要开始向专门的分布式系统的组合转换。这是一次很有趣的经历：我们构建、部署和运行了一个分布式图形数据库、一个分布式搜索后端、一个 Hadoop 安装以及第一代和第二代键值存储。

在这一切中，我学到的最有用的一件事是，我们构建的许多东西的核心都有一个非常简单的概念: 日志。有时被称为写前日志或提交日志或交易日志，几乎和计算机系统影响不离，并且是许多分布式数据系统和实时应用程序架构的核心。

如果不了解日志，你就无法完全理解数据库、NoSQL 存储、键值存储、复制、paxos、hadoop、版本控制或几乎任何软件系统，然而，大部分工程师都不是很了解。在本文中，我将向您介绍有关日志的所有知识，包括什么是日志以及如何将日志用于数据集成、实时处理和系统构建。

### 第一部分：什么是日志？

日志可能是最简单的存储抽象。它是一个按时间顺序排列的记录的全顺序的仅追加的序列。就像下面这样：

![log pic](https://content.linkedin.com/content/dam/engineering/en-us/blog/migrated/log.png)

记录被附加到日志的末尾，并且从左到右读取。每个条目都有一个惟一的顺序日志条目编号。记录的顺序定义了“时间”的概念，因为左边的条目被定义为比右边的条目更旧。日志条目编号可以看作是条目的“时间戳”。将这种顺序描述为时间的概念乍一看有点奇怪，但它有一个方便的特性，即它与任何特定的物理时钟都是解耦的。当我们接触到分布式系统时，这个特性将被证明是必不可少的。

// todo

---

原文: [The Log: What every software engineer should know about real-time data's unifying abstraction](https://engineering.linkedin.com/distributed-systems/log-what-every-software-engineer-should-know-about-real-time-datas-unifying)
