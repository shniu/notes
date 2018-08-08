
# EOS Developer Docs Translate

> EOS developers: https://developers.eos.io/

EOSIO 是一个免费的、开源的区块链软件协议，它提供给了开发者和企业一个在其之上构建、部署及运行的高性能去中心化应用的平台。

## 1. 什么是 EOSIO ?

> Referenced to https://developers.eos.io/eosio-home/docs/what-is-eos

EOSIO 软件引入了一个新的区块链架构设计，这种设计能垂直和水平扩展去中心化应用。这是通过创建一个类操作系统的构造来实现的，这个构造之上可以构建应用程序。该软件提供了账户、身份验证、数据库、异步通信和跨多个CPU或集群的应用程序调度。由此产生的技术是一个区块链体系结构，该体系结构可能最终扩展到百万级的 TPS, 消除用户费用，并允许在受治理的区块链上下文中，轻松快速部署和维护去中心化的应用程序。

## 2. Benefits

> Referenced to https://developers.eos.io/eosio-home/docs/benefits

// Todo

### 2.1 支持数百万用户

### 2.2 免费使用

### 2.3 易于升级和 Bug 恢复

### 2.4 低延时

### 2.5 串行性能

### 2.6 人类可读的账户名称

### 2.7 宪法

### 2.8 链间通信

EOSIO 软件旨在促进链间通信。这通过使 Action 存在证明和 Action 顺序证明更加容易来实现的，这些证明与围绕 Action 传递设计的应用架构相结合，可以为应用程序开发人员隐藏链间通信和证明验证的细节，从而可以向开发人员提供高级抽象。

## 3. 网络概述

> Referenced to https://developers.eos.io/eosio-home/docs/network-overview

- 简述

EOSIO 网络是由一个去中心化的共识算法(DPoS), 一个网络资源管理系统，治理流程和使用网络的一系列激励组成。

- DPoS

- 提案

- 激励

- Staking resources

EOSIO 区块链上，有3类资源需要被应用消费：
  1. 带宽和日志存储
  2. CPU
  3. RAM
  
- Staking Consumption

EOS.IO 软件允许每个帐户消耗一定百分比的可用容量，该容量与3天赌注合同中持有的令牌数量成比例。例如，如果 EOS.IO 区块链上的帐户持有根据该区块链可分配的总代币的1％，则该帐户有可能利用1％的状态存储容量。

虽然可以委派网络和计算，但是应用程序状态的存储将要求应用程序开发者持有令牌或者放置它们，直到删除该状态。 如果状态永远不会被删除，那么令牌就会被有效地从循环中移除。

- 治理

---

## EOSIO 高级特性引导

### Full Scale Network

全面的 EOSIO 网络有很多服务器组成（世界范围内可能有几千台），执行各种功能。

EOSIO 网络在这里以层状同心圆的形式描述，其中最内层是 EOSIO 核心网络，封装在 EOSIO 接入网络中，而接入网络又由 EOSIO 消费者者的全球社区访问。

![Full Scale Network](https://files.readme.io/81a9b0f-EOSIO-network-layered-diagram.png)


### BIOS Boot Sequence

> Referenced to https://developers.eos.io/eosio-nodeos/docs/bios-boot-sequence

#### Step 1 配置初始的 nodeos 节点集合

在这个教程中，我们会启动一些 `nodeos` 节点，将他们指向彼此，并最终对一系列生产者进行投票。所有的 `nodeos` 节点将都跑在同一个服务器上。下面我们会准备候选节点

- 1a. 给每一个 nodeos 创建配置和数据目录

```
mkdir ~/eosio_boot_seq_test
for (( i = 1; i <= 5; i++ )); do for (( j = 1 ; j <=5 ; j++ )); do mkdir ~/eosio_boot_seq_test/accountnum$i$j; done; done
```

后面会在启动 nodeos 时使用到，结合参数  `--config-dir` and `--data-dir`。

- 1b. 为 p2p 通信准备 ip 地址

在启动区块生产者时，我们希望能够配置他们来指向彼此，从而能够进行 P2P 通信。如果设置全网状配置，则每个节点将指向所有其他节点。全网状网络将很快变得笨拙。 以下描述说明如何将节点指向其他节点以创建对等通信。 将此选项用于您选择作为对等项的节点，但建议不要对所有节点使用此节点，因为您的配置会爆炸式增长。

