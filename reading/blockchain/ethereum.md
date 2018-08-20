
### 以太坊官方资源

- [以太坊官网](https://ethereum.org/)
- [以太坊 Github](https://github.com/ethereum)
- [Solidity](https://solidity.readthedocs.io/en/latest/index.html)
- [ERC20 Token Standard](https://theethereum.wiki/w/index.php/ERC20_Token_Standard)
- [MyEtherWallet](https://github.com/MyEtherWallet)
- [Mist](https://github.com/ethereum/mist)
- [EthFans wiki](https://github.com/EthFans/wiki/wiki)


- [以太坊白皮书中文版](https://github.com/ethereum/wiki/wiki/%5BSimplified-Chinese%5D-Ethereum-TOC)
- [以太坊黄皮书English-根本看不懂](https://ethereum.github.io/yellowpaper/paper.pdf)


### 以太坊令牌标准

- [什么是 ERC20 以太坊令牌标准](http://shniu.github.io/2018/02/27/Part1%20What%20is%20the%20ERC20%20Ethereum%20Token%20Standard/)
- [ERC20 Token 标准](http://shniu.github.io/2018/02/27/Part2%20eip-20%20ERC20%20Token%20Starndard/)
- [ERC20 Token 标准例子及解读](http://shniu.github.io/2018/02/28/Part3%20ERC20%20Token%20Standard%20Example/)


### 教程

- [以太坊是什么 - 以太坊开发入门指南](https://learnblockchain.cn/2017/11/20/whatiseth/)
- [如何通过以太坊智能合约来进行众筹（ICO）](https://learnblockchain.cn/2018/02/28/ico-crowdsale/)
- [Browser-Solidity是一个浏览器的Solidity IDE](https://ethereum.github.io/browser-solidity/)  已经迁移到  http://remix.ethereum.org
- [智能合约开发环境搭建及Hello World合约](https://learnblockchain.cn/2017/11/24/init-env/)

```
pragma solidity ^0.4.18;

contract hello {
    
    string greeting;
    
    constructor(string _greeting) public {
        greeting = _greeting;
    }
    
    function say() constant public returns (string) {
        return greeting;
    }
    
}
```

- [以太坊客户端Geth命令用法-参数详解](https://learnblockchain.cn/2017/11/29/geth_cmd_options/)
- [Geth 控制台使用及 Web3.js 使用实战](https://learnblockchain.cn/2017/12/01/geth_cmd_short/#more)
- [智能合约语言 Solidity 教程系列1 - 类型介绍](https://learnblockchain.cn/2017/12/05/solidity1/#more)
- [Truffle ](https://truffleframework.com/)
- [Truffle 中文文档](http://truffle.tryblockchain.org/)
- [https://blog.csdn.net/shebao3333/article/details/79993886](https://blog.csdn.net/shebao3333/article/details/79993886)
- 

- [以太坊的工作原理](https://lilymoana.github.io/ethereum_theory.html)
- [Ethereum 账户、地址、私钥和公钥](http://blog.luoyuanhang.com/2018/04/17/eth-basis-accounts-address-pubkey-prikey/)


### 智能合约方面

- [安全编写以太坊的智能合约指南](http://me.tryblockchain.org/onward-with-ethereum-smart-contract-security.html)

这篇文章中描述的安全模式有：

1. 尽早且明确的暴露问题。
2. 使用（pull）模式而不是（push）模式, [pull模式代码的例子](https://github.com/OpenZeppelin/openzeppelin-solidity/blob/master/contracts/payment/PullPayment.sol)
3. 代码结构遵从：条件，行为，交互
4. 注意平台限制
5. 测试用例
6. 容错及自动bug奖励
7. 限制存入的资金
8. 简单与模块化的代码
9. 不要从零开始写代码
10. openzeppelin-solidity 上有一些编写合约的最佳实践 https://github.com/OpenZeppelin/openzeppelin-solidity, a framework to build secure smart contracts on Ethereum
11. [Understanding Serenity, Part I: Abstraction](https://blog.ethereum.org/2015/12/24/understanding-serenity-part-i-abstraction/)

https://github.com/OpenZeppelin/openzeppelin-solidity/tree/master/contracts
https://github.com/OpenZeppelin/openzeppelin-solidity/tree/master/contracts/token/ERC20

- [Ethereum Smart Contract Security Best Practices](https://consensys.github.io/smart-contract-best-practices/)

总结的全面; // todo

- [如何编写一个可升级的智能合约](https://learnblockchain.cn/2018/03/15/contract-upgrade/)

数据合约及控制合约双管齐下

- [智能合约的可升级改造实践](https://blog.csdn.net/uxiAD7442KMy1X86DtM3/article/details/78932234)


- [5分钟在以太坊上发行自己的代币](http://wangmengqi.logdown.com/posts/2473648-tutorial-workshop-on-smart-contracts-issued-tokens)

使用metamask 和 http://tokenfactory.surge.sh/#/factory

- [ERC20 token 官方合约代码](https://www.ethereum.org/token)
- [Raising funds from friends without a third party](https://www.ethereum.org/crowdsale)
- [How to build a DEMOCRACY on the blockchain](https://www.ethereum.org/dao)
- [DAPP state](https://www.stateofthedapps.com/)   里面有很多跑在以太坊的DAPP

- [如何根据ERC20标准进行以太坊代币开发和发行](https://segmentfault.com/a/1190000014916210)
- [以太坊 JavaScript API web3.js 打币](https://88250.b3log.org/articles/2018/03/09/1520583695227.html)
- [以太坊代币空投合约实现](https://segmentfault.com/a/1190000014720301)
- [详解ERC20与ERC721](https://zhuanlan.zhihu.com/p/35019858)
- [以太坊ERC20 Token标准完整说明](https://blog.csdn.net/diandianxiyu_geek/article/details/78082551)
- [B3T 合约代码](https://github.com/b3log/chainbook/blob/master/contract/B3T.sol)
- [如何在私有区块链上编写、部署以及与以太坊进行交互的智能合约](https://blog.csdn.net/dev_csdn/article/details/78893014)


---

- [以太坊智能合约升级策略 - 权威指南](http://blog.hubwiz.com/2018/04/27/ethereum-contract-upgrade-strategy/)

  两种主要策略用来实现可升级的智能合约：
  - 使用代理合约
  - 将逻辑和数据分离成不同的合约
  - 
  

- [账户、交易核心概念及投注合约解析](http://wangxiaoming.com/blog/2016/07/21/e21/)  一些比较深入的解释
- [最小可行区块链原理解析1](http://wangxiaoming.com/blog/2017/01/03/minimum-viable-blockchain-1/)
- [最小可行区块链原理解析2](http://wangxiaoming.com/blog/2017/01/09/minimum-viable-blockchain-2/)
- [最小可行区块链原理解析3](http://wangxiaoming.com/blog/2017/01/18/minimum-viable-blockchain-3/)


###### Solidity 

- [Solidity的全局变量](http://solidity.readthedocs.io/en/develop/units-and-global-variables.html)

> Block and Transaction Properties
> - block.blockhash(uint blockNumber) returns (bytes32): hash of the given block - only works for 256 most recent, excluding current, blocks - deprecated in version 0.4.22 and replaced by blockhash(uint blockNumber).
> - block.coinbase (address): current block miner’s address
> - block.difficulty (uint): current block difficulty
> - block.gaslimit (uint): current block gaslimit
> - block.number (uint): current block number
> - block.timestamp (uint): current block timestamp as seconds since unix epoch
> - gasleft() returns (uint256): remaining gas
> - msg.data (bytes): complete calldata
> - msg.gas (uint): remaining gas - deprecated in version 0.4.21 and to be replaced by gasleft()
> - msg.sender (address): sender of the message (current call)
> - msg.sig (bytes4): first four bytes of the calldata (i.e. function identifier)
> - msg.value (uint): number of wei sent with the message
> - now (uint): current block timestamp (alias for block.timestamp)
> - tx.gasprice (uint): gas price of the transaction
> - tx.origin (address): sender of the transaction (full call chain)

- [以太坊 Solidity 合约 call 函数簇滥用导致的安全风险](https://paper.seebug.org/633/)
- [如何渗透测试以太坊 dApps](https://paper.seebug.org/612/)
- [以太坊智能合约安全入门了解一下（下）](https://paper.seebug.org/607/)
- [浅谈以太坊（ethereum）智能合约（Smart Contract）的设计模式与升级方法](http://liyuechun.org/2017/11/01/eth-design-pattern/)
- [Mastering Ethereum, by Andreas M. Antonopoulos, Gavin Wood](https://github.com/ethereumbook/ethereumbook)
- [Solidity 中 revert(), assert() 和 require() 的使用方法](https://ethfans.org/posts/when-to-use-revert-assert-and-require-in-solidity)

###### 以太坊架构分析

- [以太坊网络架构解析](https://paper.seebug.org/642/)
- [Microsoft Azure 以太坊节点自动化部署方案漏洞分析](https://paper.seebug.org/638/)
- [安全团队的漏洞分析，非常不错](https://paper.seebug.org/category/blockchain/?page=4)
- [以太坊智能合约安全 Dasp Top10](https://paper.seebug.org/603/)

  > 1. 重⼊漏洞 (如DAO)
  > 2. 访问控制

---

- [以太坊源代码分析 - I.区块和交易，合约和虚拟机](https://blog.csdn.net/teaspring/article/details/75389151)
- [以太坊源代码分析 - II. 数据的呈现和组织，缓存和更新](https://blog.csdn.net/teaspring/article/details/75390210)

- [Merkle Patricia Tree (MPT) 树详解](https://www.cnblogs.com/fengzhiwu/p/5584809.html)

https://blog.csdn.net/qq_33935254/article/details/55505472

- [以太坊源码分析-以太坊启动](https://bitshuo.com/topic/5975fbb14a7a061b785db8d5)
- [以太坊源码分析-转账流程分析](https://bitshuo.com/topic/597affb54a7a061b785db940)
- [以太坊源码剖析：交易](https://bitshuo.com/topic/5948897c8e822fcb444317d0)
- [以太坊源码分析（2）账户模块](https://bitshuo.com/topic/590225289a87f1a331fa2a83)
- [以太坊源码分析（3）深入解析ABI](https://bitshuo.com/topic/594a8c228e822fcb444317ec)
- [以太坊源码分析（4）bootnode](https://bitshuo.com/topic/5953ca664a7a061b785db707)
- https://github.com/ZtesoftCS/go-ethereum-code-analysis/blob/master/accounts%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90.md
- [go-ethereum-code-analysis](https://github.com/ZtesoftCS/go-ethereum-code-analysis)
- [VI. 基于p2p的底层通信(上篇)](https://blog.csdn.net/teaspring/article/details/78455046)


###### Web3.js

- [web3公测版本教程（二）-基础异步语法，交易，签名交易，ganache-cli](https://segmentfault.com/a/1190000013841707)
- [区块链钱包之ETH交易离线签名](https://blog.csdn.net/qq634416025/article/details/79686032)
- [web3.js与合约交互](https://blog.csdn.net/weixin_39842528/article/details/81034972)
- [如何使用web3部署以太坊智能合约](https://segmentfault.com/a/1190000013841167?utm_source=channel-newest)
- https://github.com/b3log/chainbook/blob/master/contract/B3T.sol

##### 开发

- [区块链一键登录：MetaMask教程(One-click Login with Blockchain: A MetaMask Tutorial)](https://windcoder.com/qukuailianyijiandenglumetamaskjiaocheng)
- [使用 Web3 和 Vue.js 来创建你的第一个以太坊 dAPP](https://www.oschina.net/translate/create-your-first-ethereum-dapp-with-web3-and-vue-js-part)
- [使用 MetaMask, web3 和 EthJS 呼叫合約](https://medium.com/@anderson.c/ethereum-%E6%99%BA%E8%83%BD%E5%90%88%E7%B4%84%E9%96%8B%E7%99%BC%E7%AD%86%E8%A8%98-%E9%80%8F%E9%81%8E-metamask-%E4%BD%BF%E7%94%A8-web3-ethjs-64b73aa8d3a)

- [Create your first Ethereum dAPP with Web3 and Vue.JS (Part 1)](https://itnext.io/create-your-first-ethereum-dapp-with-web3-and-vue-js-c7221af1ed82)
- [Create your first Ethereum dAPP with Web3 and Vue.JS (Part 2)](https://itnext.io/create-your-first-ethereum-dapp-with-web3-and-vue-js-part-2-52248a74d58a)
- [Create your first Ethereum dAPP with Web3 and Vue.JS (Part 3)](https://itnext.io/create-your-first-ethereum-dapp-with-web3-and-vue-js-part-3-dc4f82fba4b4)

```
https://github.com/kyriediculous/dapp-tutorial/tree/master/src/components

// 我的 metamask 两个账号
1: 0xbCc07B7274653F34471c80B721211A76254C24C9
2: 0x8584F02679CF22dc97C832ccdBEc3BCCe2ce8d0e

// -- Injected VM 使用 metamask
// 合约地址
0x815C438A7A63E2Da9F5E05583E40EaDC63D5353A
// 创建者
0xbcc07b7274653f34471c80b721211a76254c24c9


// -- Javascript VM  本地模拟环境
// Casino
0xca35b7d915458ef540ade6068dfe2f44e8fa733c
// 合约地址
0x692a70d2e424a56d2c6c27aa97d1a86395877b3a
```

- [去中心化交易所 Etherdelta 工作原理与设计架构](http://www.26595.com/Caijin_Zhengquan/zhuanlan/3410443345-9404.htm)

> 首先，搜集所有的挂单（Order），如果有其它使用者看到感兴趣的挂单，则可以申请与之交易（Trade）。如果挂单没有人问津，则挂单者可以取消（Cancel）。另外，智能合约只能操作自己账户的余额，无法操作其它账户，所以若要交易，需要先入金（Deposit），交易结束后可以通过出金（Withdraw）提出账户余额。
出金入金的逻辑比较简单。合约用一个二维mapping结构来记录使用者的余额，余额只有当存钱、取钱和交易成功情况下才会有变更。除此之外，在存入ERC20代币时，并不是直接把ERC20代币转入合约，而是通过调用tranferFrom授权交易所操作用户账户。挂单是告诉智能合约，用户需要将多少代币换成以太币（或相反）。当有人愿意交易时，才会触发余额状态的改变。
> 1. 用户A调用代币合约的approve函数，让交易所合约可以操作用户A的代币。 2. 用户A调用交易所合约的depositToken函数，让交易所合约转移A的代币到交易所合约，更新余额状态后，用户A入金完毕。 3. 用户B调用交易所合约的order挂单，表示希望以token换以太币。 4. 用户B调用交易所合约的deposit，存入相对应的以太币。 5. 用户B调用交易所合约的trade，并附上3生成的单号，交易所合约更新双方余额后交易成功。 6. 用户A调用交易所合约的withdraw，提出以太币。 7. 用户B调用交易所合约的withdrawToken，提出token。

###### 私有网络创建

```
// 1. genesis.json
{
 "alloc": {},
 "config": {
   "chainID": 72,
   "homesteadBlock": 0,
   "eip155Block": 0,
   "eip158Block": 0
 },
 "nonce": "0x0000000000000000",
 "difficulty": "0x4000",
 "mixhash": "0x0000000000000000000000000000000000000000000000000000000000000000",
 "coinbase": "0x0000000000000000000000000000000000000000",
 "timestamp": "0x00",
 "parentHash": "0x0000000000000000000000000000000000000000000000000000000000000000",
 "extraData": "0x11bbe8db4e347b4e8c937c1c8370e4b5ed33adb3db69cbdb7a38e1e50b1b82fa",
 "gasLimit": "0xffffffff"
}

// 在web3 console里内置了一些用来操作以太坊的Javascript对象。主要有：

eth：包含一些跟操作区块链相关的方法
net：包含以下查看p2p网络状态的方法
admin：包含一些与管理节点相关的方法
miner：包含启动&停止挖矿的一些方法
personal：主要包含一些管理账户的方法
txpool：包含一些查看交易内存池的方法
web3：包含了以上对象，还包含一些单位换算的方法

```

### 一些注意事项

#### 关于以太坊账户(外部账户&合约账户)的nonce值

nonce值的存在主要是因为它是基于account的，不同于基于utxo的比特币。nonce值主要用来防止重放攻击。

- 外部账户每发送一笔交易nonce加一。
- 合约账户每创建一个合约nonce加一。而合约调用其他合约属于内部调用，因此nonce值不变。


#### 账户

以太坊中有两类账户，它们共用同一个地址空间。外部账户，该类账户被公钥-私钥对控制（人类）。合约账户，该类账户被存储在账户中的代码控制。

外部账户的地址是由公钥决定的，合约账户的地址是在创建改合约时确定的（这个地址由合约创建者的地址和该地址发出过的交易数量计算得到，地址发出过的交易数量也被称作"nonce"）
合约账户存储了代码，外部账户则没有，除了这点以外，这两类账户对于EVM来说是一样的。

每个账户有一个key-value形式的持久化存储。其中key和value的长度都是256bit，名字叫做storage.

另外，每个账户都有一个以太币余额（单位是“Wei"），该账户余额可以通过向它发送带有以太币的交易来改变。

- [ETC转到ETH地址以及转币进ETH智能合约账户能不能转出来?](https://www.jinse.com/bitcoin/193782.html)
