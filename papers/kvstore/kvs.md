
### Papers

#### KVS

- [kvs_bitcask-intro.pdf](https://github.com/basho/bitcask/tree/develop/doc)

也可以参考：http://highscalability.com/blog/2011/1/10/riaks-bitcask-a-log-structured-hash-table-for-fast-keyvalue.html

Bitcask 是一个日志型结构的 Hash 表实现，可以快速访问 Key/Value 数据，可以根据 Bitcask 论文中的设计思路来实现一个 Key/Value 存储引擎。

Bitcask 的设计初衷是为了在 Riak 分布式数据库中使用的，作为一个插件集成到 Riak；Bitcask 被设计成为一个单机版的存储引擎，可以方便的和其他系统做集成。Riak 可以支持很多 Backend Storage Engine, 如 Bitcask/leveldb etc. 

同时，Bitcask设计初衷意在解决小文件存储的效率低下问题，而小文件存储效率低下的原因除了文件随机IO较多外，还容易由于数量的暴涨而导致元数据(dentry/inode)开销的增大，直接影响读写效率。为了解决该问题，文件被以日志方式追加至大文件中，大大减少文件系统元数据开销。同时Bitcask管理自身文件索引：维护文件key至在磁盘文件位置及文件内偏移

1. Bitcask 的强项看[这里](https://docs.basho.com/riak/kv/2.0.0/setup/planning/backend/bitcask/#bitcask-s-strengths)
2. Bitcask 的弱点看[这里](https://docs.basho.com/riak/kv/2.0.0/setup/planning/backend/bitcask/#weaknesses), 另外 Bitcask 还不支持范围查询，也是一个弱点
3. [这里](https://docs.basho.com/riak/kv/2.0.0/setup/planning/backend/bitcask/#configuring-bitcask)是Bitcask的一些具体实现上的配置和考量，非常值得看；包括：Bitcask 的路径配置，打开超时处理，同步策略，
4. 日志型 Key/Value Store 的存储引擎比较重要的是合并策略，Riak 中可以设置的 Bitcask 的合并策略看：[Merging settings](https://docs.basho.com/riak/kv/2.0.0/setup/planning/backend/bitcask/#disk-usage-and-merging-settings), [Merge Policy](https://docs.basho.com/riak/kv/2.0.0/setup/planning/backend/bitcask/#merge-policy), [Merge triggers](https://docs.basho.com/riak/kv/2.0.0/setup/planning/backend/bitcask/#merge-policy), [Merge thresholds](https://docs.basho.com/riak/kv/2.0.0/setup/planning/backend/bitcask/#merge-thresholds), [Log needs merge](https://docs.basho.com/riak/kv/2.0.0/setup/planning/backend/bitcask/#log-needs-merge)
5. 调优Bitcask，[Tuning Bitcask](https://docs.basho.com/riak/kv/2.0.0/setup/planning/backend/bitcask/#log-needs-merge)
6. [Bitcask 实现细节](https://docs.basho.com/riak/kv/2.0.0/setup/planning/backend/bitcask/#bitcask-implementation-details)
7. [Riak KV](https://docs.basho.com/riak/kv/2.0.0/learn/why-riak-kv/)
8. 基于 Bitcask 设计的实现有: [Riak 的 Bitcask backend](https://github.com/basho/bitcask), [Douban beansDB](http://www.d-kai.me/%E4%BC%98%E9%9B%85%E7%9A%84bitcaskbeansdb/)

- [Google Big Table](https://storage.googleapis.com/pub-tools-public-publication-data/pdf/68a74a85e1662fe02ff3967497f31fda7f32225c.pdf)

虽然 BigTable 不是一个单纯的 KVS，但是它实现了一个类似于 LevelDB 的存储引擎，阐述了 SSTable 的设计，非常值得借鉴，也是搞懂 SSTable 的入门文章。

- [Leveldb](https://github.com/google/leveldb)

Leveldb 是一个由 Google 推出的 kvs 存储引擎，它提供了一个按 key 排序的 kvs，并且支持任意的字节数组的 key 和 value。Leveldb 要解决 PB 级数据大小的快速随机访问：[怎么解决这个问题](SSTable and Log Structured Storage: LevelDB)。

Leveldb 实现的关键点是：
1. MemTable，来支持高效的随机写入
2. WAL，预写日志来解决突发问题，比如突然断电、服务进程异常退出等，容易做崩溃恢复
3. MemTable + SSTable index + Bloom Filters + SSTable File，来支持高效的随机读取
4. Merge + Compression，来达到空间收缩
5. Sorted String Table，来支持区间查找，可以前向和后向遍历
6. 操作接口简单易用

这个总结非常好，[存储引擎技术架构与内幕](https://github.com/abbshr/abbshr.github.io/issues/58)

- [LSM Tree](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.44.2782&rep=rep1&type=pdf)

LSM Tree 论文是 96 年一帮人写的，很长，读起来有点费劲。

论文太难，入门开[这个](http://www.open-open.com/lib/view/open1424916275249.html)，英文版的出处在[这里](http://www.benstopford.com/2015/02/14/log-structured-merge-trees/)

然后就是 [wiki](https://en.wikipedia.org/wiki/Log-structured_merge-tree), [Leveled Compaction in Apache Cassandra](https://www.datastax.com/dev/blog/leveled-compaction-in-apache-cassandra)

Log-Structured Merge Tree, 一般理解上应该是一种算法设计思想，描述了如何解决大数据下的快速随机读写访问；首先设计之初就考虑借助磁盘顺序读写的高性能（相比于磁盘的随机读写，几个数量及上的差距，不管是SSD还是普通磁盘），利用这个优势；然后解决快速读写的问题，高效写入借助内存表memTable, 将最近写入的热数据放在内存中，只写内存，为了应对突发状况使用 WAL，内存表是有序的，一般可以使用 SkipList 实现，然后通过两次 Compacation 将内存中的数据 flush 到磁盘中，形成一个个的 SSTable 文件，Compacation 的操作是在满足特定条件后触发在后台默默完成的；最后就是高效读操作，每个 SSTable 文件都会有一个常驻内存的索引，使用索引来快速定位数据，当然为了应对性能下降，引入了布隆过滤器，可以提升一定的读性能。这就是 LSM 的基本思想。

原理相对来讲比较简单，但是要实现一个这样的存储引擎并非容易的事情。目前使用 LSM Tree 的设计思想实现的数据存储有： Bigtable, HBase, LevelDB, MongoDB, SQLite4, Tarantool, RocksDB, WiredTiger, Apache Cassandra, and InfluxDB

郑老师写了几篇文章对 LSM Tree 做了分析：
1. [LSM-tree存储引擎的优化研究成果总结(1)](https://mp.weixin.qq.com/s/uUFeK2ptyG7r8Fnmsry3Sw)
2. [LSM-tree存储引擎的优化研究成果总结(2) -- 关于索引空间的优化](https://mp.weixin.qq.com/s/hQomSlxzzPn9pNmqCxMO3g)
3. [LSM-tree存储引擎的优化研究成果总结(3) -- 架构的优化](https://mp.weixin.qq.com/s/8mRo94B-UAnSfYqvlw5rjg)

- RocksDB

RocksDB 是一个基于 LevelDB 开发的内嵌数据库引擎，可以快速读写访问。

1. [Wiki](https://en.wikipedia.org/wiki/RocksDB) 里有丰富的资源。
2. [RocksDB Github](https://github.com/facebook/rocksdb)
3. [RocksDB's wiki](https://github.com/facebook/rocksdb/wiki)
4. [获得PCC性能大赛背后的RocksDB引擎:5分钟全面了解其原理](https://sdk.cn/news/6686)

- CockroachDB

1. [Github](https://github.com/cockroachdb/cockroach)  Go 语言实现
2. https://www.cockroachlabs.com
3. 膜拜一下[设计](https://github.com/cockroachdb/cockroach/blob/master/docs/design.md)
4. [中文社区](http://www.cockroachchina.cn/)
5. http://www.cockroachchina.cn/?p=1242

- Cassandra

- [Data Structures and Algorithms for Big Databases](https://people.csail.mit.edu/bradley/BenderKuszmaul-tutorial-xldb12.pdf)

Very great! [Data Structures and Algorithms for Big Databases 摘要](https://dirtysalt.github.io/html/data-structures-and-algorithms-for-big-databases.html)

- [Amazon Dynamo: Highly Available Key-value Store](https://www.allthingsdistributed.com/files/amazon-dynamo-sosp2007.pdf)

- [A Survey Paper on NoSQL Databases: Key-Value Data Stores and Document Stores](http://www.ijrat.org/downloads/Vol-6/feb-2018/paper%20ID-62201812.pdf)

#### File System

- [fs_The Google File System](https://ai.google/research/pubs/pub51.pdf)