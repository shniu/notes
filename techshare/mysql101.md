
## MySQL

### 连接相关

#### 常用命令

- show processlist

作用是查询用户正在运行的线程

```
// information_schema 库的 processlist 表
show processlist;   // 返回的每一列的含义很重要
select * from information_schema.processlist;

// 按客户端ip分组，查询哪个客户端的连接数最多
select client_ip,count(client_ip) as client_num from (select substring_index(host,':' ,1) as client_ip from processlist ) as connect_info group by client_ip order by client_num desc;

// 查看正在执行的线程，并按Time倒序
select * from information_schema.processlist where Command != 'Sleep' order by Time desc;

// 找出所有执行时间超过 5 分钟的线程，拼凑出 kill 语句
select concat('kill ', id, ';') from information_schema.processlist where Command != 'Sleep' and Time > 300 order by Time desc;

```

#### Cases

- [MySQL SHOW PROCESSLIST协助故障诊断全过程](https://www.jb51.net/article/156313.htm)
- https://zhuanlan.zhihu.com/p/30743094
- [Thread state](https://dev.mysql.com/doc/refman/5.6/en/general-thread-states.html)
- https://dev.mysql.com/doc/refman/5.6/en/thread-commands.html


### redo log 相关

// ---
innodb_flush_log_at_trx_commit 这个参数指定了 InnoDB 在事务提交后的日志写入频率
// 0 表示log buffer 会 每秒写入到日志文件并刷写（flush）到磁盘。但每次事务提交不会有任何影响，也就是 log buffer 的刷写操作和事务提交操作没有关系。在这种情况下，MySQL性能最好，但如果 mysqld 进程崩溃，通常会导致最后 1s 的日志丢失。
// 1 表示每次事务提交时，log buffer 会被写入到日志文件并刷写到磁盘。这也是默认值。这是最安全的配置，但由于每次事务都需要进行磁盘I/O，所以也最慢。
// 2 表示每次事务提交会写入日志文件，但并不会立即刷写到磁盘，日志文件会每秒刷写一次到磁盘。这时如果 mysqld 进程崩溃，由于日志已经写入到系统缓存，所以并不会丢失数据；在操作系统崩溃的情况下，通常会导致最后 1s 的日志丢失。
这个参数具体设置成什么，需要根据实际的场景来区分对待。如支付服务，对一致性和完整性要求很高，所以即使最慢，也最好设置为 1.对于一些数据一致性和完整性要求不高的应用，配置为 2 就足够了；如果为了最高性能，可以设置为 0。
// Ref
// http://liyangliang.me/posts/2014/03/innodb_flush_log_at_trx_commit-and-sync_binlog/

### binlog 相关

sync_binlog 是MySQL 的二进制日志（binary log）同步到磁盘的频率。MySQL server 在 binary log 每写入 sync_binlog 次后，刷写到磁盘。可选值 0 1 N
// 对于支付服务这样的应用，还是比较推荐 sync_binlog = 1.
// Ref
// https://dev.mysql.com/doc/refman/5.5/en/replication-options-binary-log.html#sysvar_sync_binlog 

