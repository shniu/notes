

## EOS Ram 分析笔记

本文会按照如下结构进行组织

- 在 EOS 上如何买卖 Ram 以及如何查看自己拥有的 Ram
- EOS 系统内置的4大合约
- EOS Ram 的实现源码分析
- 在 EOS 上我们是否可以实现类似 Ram 这种机制的合约？

对于 EOS Ram 是什么以及他为何可以被炒作就不废话了，仅从技术角度去分析 Ram 的实现机制。

### 在 EOS 上如何买卖 Ram

这个事情很简单，EOS 目前有主网和测试网络，去实际验证一下还是很方便的。作为技术人员，我们使用 EOS 自身提供的命令行工具来完成买卖 Ram，以下操作在 EOS 的测试网络 Jungle Testnet 上完成。

可以参考这里的文档：[Jungle Testnet 操作命令](https://github.com/shniu/eosio/tree/master/jungletestnet)

##### 前提条件

- 已经在 Jungle Testnet 上申请了账号，意味这你有了账号和公私钥
- 在自己的电脑上安装了eos，可以使用 eos 的 docker 镜像来启动，最方便，镜像为 eosio/eos 或者 eosio/eos-dev

##### 买卖

首先介绍一下 cleos，cleos 是 EOS 软件的客户端程序，使用该命令行工具可以去操作 EOS 网络。其中我们会使用的一个命令是 system，这个命令是由 EOS 的系统合约来支持的，下面开始：

```
/// 我想购买 1 EOS 的 Ram
/// 解释一下：creditotoken creditotoken "1 EOS"
/// 第一个 creditotoken 代表： 花钱购买 Ram 的账户
/// 第二个 creditotoken 代表： 接受 Ram 的账户，意思就是你可以拿你的账户给另外一个账户买 Ram，也可以给当前账户买
/// 最后一个代表：花费多少 EOS 买Ram，对应的 EOS 在网络中会使用 Bancor 算法自动计算出来能够购买多少 Ram
cleos system buyram creditotoken creditotoken "1 EOS"

/// 当然也可以直接指定购买多少的 kBytes 的内存
cleos system buyram creditotoken creditotoken "1024 kbytes" --kbytes


/// 我们来查看一下现在有多少 Ram 在账户中
cleos get account creditotoken
permissions:
     owner     1:    1 EOS7KLp9T9zYPCmVXrHXyizbg8Qh5HNfb9d2adiCDjSWMMBnr2VQA
	 Active     1:    1 EOS7KLp9T9zYPCmVXrHXyizbg8Qh5HNfb9d2adiCDjSWMMBnr2VQA
memory:
     quota:     122.5 KiB    used:     3.051 KiB    <---- 这里就是拥有的内存

net bandwidth:
    delegated:       1.0000 EOS           (total staked delegated to account from others)
    used:               364 bytes
    available:        191.6 KiB
    limit:              192 KiB
cpu bandwidth:
    delegated:       1.0000 EOS           (total staked delegated to account from others)
    used:             13.02 ms
    available:        24.43 ms
    limit:            37.44 ms

EOS balances:
    liquid:          489.2485 EOS
    staked:            0.0000 EOS
    unstaking:         0.0000 EOS
    total:           489.2485 EOS


/// 现在市场价上去了，我想卖掉，赚个价差
/// 很简单，creditotoken 代表接受卖掉 Ram 后得到的 EOS 的账户
/// 1024 代表卖掉多少 kbytes 的 Ram
cleos system sellram creditotoken 1024 -p creditotoken

```

好了，这样就买卖完了。下面补充一些简单的解释：

> RAM 的买卖，实质上是抵押 eos 到系统账户，而不是买方和卖方直接的交易。不论是购买 ram (即抵押 eos，获取 ram)，还是卖出 ram(即取回抵押的 eos，释放 ram)，都是参与者与系统账户之间的交互，该过程将会收取 0.5% 的手续费。RAM 的价格是基于 Bancor 算法，可简单理解为市场的供需模型。如果 RAM 供不应求，则买入 RAM 时就需要锁定更多的 EOS；同时，卖出 RAM 也能获得更多的 EOS。

### EOS 系统内置的4大合约

上面简单过了一下关于 Ram 的买卖，要继续了解 Ram 的机制，先来了解一下系统的内置合约。

EOS 是一个比较复杂的区块链系统，运用了很多计算机知识。其中 EOS 采用了插件式设计。

在启动 BP 节点时，需要一系列的初始化操作，具体的可以参看 [bios boot sequence](https://developers.eos.io/eosio-nodeos/v1.1.0/docs/bios-boot-sequence)

- eosio.bios 合约

这是一个基础合约，可以控制其他账户的资源分配和权限、限制指定账户或全局的资源使用、设置区块生产者。

- eosio.system 合约

  1. 用户可以抵押 token，配置和投票给区块生产者和 worker proposals
  2. 用户可以设置代理，把投票权移交给其他用户
  3. etc.

system 合约包含了很多重要的功能，如：

  1. `eosio_global_state` 全局状态, 其中就定义了 64G 的 Ram 限制，etc.
  2. `producer_info` 生产者信息
  3. `voter_info` 投票人信息
  4. 很多 action

- eosio.token 合约

提供代币的创建、发行和转账功能。

- eosio.msig 合约

该合约提供了多重签名的功能，多个账户对一起事务进行签名，可以异步提出、批准、发布经过多方同意的事务。


### EOS Ram 的实现源码分析

在 EOS 中，智能合约执行完毕后，所占用的内存会释放。程序中的所有变量都会丢失。如果智能合约里要持久地记录信息，比如游戏智能合约要记录每位用户游戏记录，本次合约执行完毕后数据不能丢失，就需要将数据存储到 EOS 数据库中。

```cpp
/// 下面的代码就是按 kbytes 方式购买内存的入口
/**
 *  This action will buy an exact amount of ram and bill the payer the current market price.
 */
void system_contract::buyrambytes( account_name payer, account_name receiver, uint32_t bytes ) {
  /// 最重要的是看那个 _rammarket，下面讲这个
  auto itr = _rammarket.find(S(4,RAMCORE));  
  auto tmp = *itr;
  
  /// 这个是将买进的内存量换算成 eos, 换算的方式是根据 Bancor 算法
  auto eosout = tmp.convert( asset(bytes,S(0,RAM)), CORE_SYMBOL );  

  /// 执行实际的买内存的操作
  buyram( payer, receiver, eosout );  
}
```

其他的分析参考如下文章：

- [EOS学习之(RAM,NET,CPU)](https://blog.csdn.net/weixin_39842528/article/details/80913141)
- [eos源码解析（二） ： bancor算法](https://steemit.com/eos/@camphortree/eos-bancor)


### 在 EOS 上我们是否可以实现类似 Ram 这种机制的合约？

要回答这个问题，需要先对 EOS 的智能合约的边界能力有清楚的认识。先来认识一下 EOS 的智能合约

##### EOS 通信模型

EOS 的智能合约是由 action 和 type definitions 的集合构成的。Action 定义和实现了合约的行为；Type definitions 定义了需要的数据和结构。EOS 是基于消息通信的，合约之间也可以进行通信；

详细看这里：[Communication Model](https://developers.eos.io/eosio-cpp/docs/communication-model)

##### EOS 消息持久化 API

EOS 根据 Boost 库中的 `Multi-Index Containers`, 开发了 `eosio::multi_index`, 多索引列表类；

在 EOS 中，为每个账户都预留了数据库空间（大小与代币持有量有关），每个账户名下可以建立多个数据表。智能合约无法直接操作存储在见证人硬盘中的数据表，需要使用 `multi_index` 作为中间工具（或者叫容器），每个 `multi_index` 
实例都与一个特定账户的特定数据表进行交互（取决于实例化时的参数）。

更加详细的关于 EOS `multi_index` 的信息看这里：

- [EOS 数据库与持久化 API —— 架构](https://blog.csdn.net/yuanfangyuan_block/article/details/80403360)
- [EOS 数据库与持久化 API —— 实战](https://blog.csdn.net/yuanfangyuan_block/article/details/80403378)
- [EOS技术研究：合约与数据库交互](https://www.cnblogs.com/Evsward/p/multi_index.html)  这里有完整的例子

##### 对 exchange 合约的解读

[EOS标准货币体系与源码实现分析](https://cloud.tencent.com/developer/article/1118567)

### Ref

- [Ram 扩容之争](http://note.youdao.com/noteshare?id=d18c077abd79b64bb8d20faf40f7dd16&sub=C832DA45278341B09B195F4EAD7AA54D)
