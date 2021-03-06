
之前的[eos笔记](http://note.youdao.com/noteshare?id=09ccaccfdff1f9bcbe46ce61a4956225&sub=82B339E6589947029478E3433AAF0517)
之前的[笔记2](http://note.youdao.com/noteshare?id=4a5a4648072bc44ee7d436760943c634&sub=69C71E350A2D49DF8FA88B67507C6718)
之前的一些[实战上的想法](http://note.youdao.com/noteshare?id=2598c5129a348621902eef1ef8b4196d&sub=24BA5E0E47504351BD60726BA7E25746)

---

## EOS

> https://medium.com/eosio/eosio-dawn-3-0-now-available-49a3b99242d7

### Key tech

#### 1. inline action

inline action简单来说就是action调用另外一个action, 具体来说就是一个智能合约的代码调用另外一个智能合约的函数。在 DAWN4.0 以后，需要使用到 `eosio.code` 虚拟权限。下面用例子演示一下

- 前提条件

已经拥有了4个账户，`ex.hello`, `ex.target`, `ex.user1`, `ex.user2`

- 编写 `ex.hello` 的合约，它需要去调用 `ex.target`

```cpp
 #include <eosiolib/eosio.hpp>                                          
                                                                        
 using namespace eosio;                                                 
                                                                        
 class excli : public eosio::contract {                                 
   public:                                                              
       using contract::contract;                                        
                                                                        
       /// @abi action                                                  
       void hi( account_name from, account_name to ) {                  
          require_auth(from);                                           
          print( "Hello from: ", name{from}, ", to: ", name{to} );      
                                                                        
          // inline action 这里调用了 ex.target, 但是需要 ex.hello@eosio.code 权限                                            
          action(                                                       
            permission_level{to, N(active)},                            
             N(ex.target), N(hi),                                       
             std::make_tuple(to)                                        
          ).send();                                                     
       }                                                                
 };                                                                     
                                                                        
 EOSIO_ABI( excli, (hi) )
```

- 编写 `ex.target` 的合约

```cpp
#include <eosiolib/eosio.hpp>                          
#include <eosiolib/print.hpp>                          
#include "exeos.hpp"                                   
                                                       
using namespace eosio;                                 
                                                       
class exeos : public eosio::contract {                 
  public:                                              
      using contract::contract;                        
                                                       
      /// @abi action                                  
      void hi( account_name user ) {                   
         require_auth(user);                           
         print( "Hello, ", name{user} );               
      }                                                
};                                                     
                                                       
EOSIO_ABI( exeos, (hi) )                               
```

- Test

```
// 部署 ex.hello 合约
cleos set contract ex.hello contracts/excli -p ex.hello

// 部署 ex.targe 合约
cleos set contract ex.target contracts/exeos -p ex.target

// 直接调用会报错，权限问题
cleos push action ex.hello hi '["ex.user1", "ex.user2"]' -p ex.user1

// 在 ex.user1@active 权限下加入 ex.hello@eosio.code 的授权
cleos set account permission ex.user1 active '{"threshold": 1,"keys": [],"accounts": [{"permission":{"actor":"ex.hello","permission":"eosio.code"},"weight":1}]}' owner -p ex.user1@owner

// 再次调用，成功
cleos push action ex.hello hi '["ex.user1", "ex.user2"]' -p ex.user1
```

#### 2. 合约与db交互

[EOS技术研究：合约与数据库交互](https://www.cnblogs.com/Evsward/p/multi_index.html)

- Ref

[Permission issue for action in contract #3013](https://github.com/EOSIO/eos/issues/3013)

[EOS智能合约开发实践之合约调用合约(inline action)](https://blog.csdn.net/itleaks/article/details/80535318)

[EOS保留权限eosio.code深度解读](https://blog.csdn.net/itleaks/article/details/80557560)

[EOS权限模型机制分析](https://blog.csdn.net/itleaks/article/details/80422288)

![inline action](https://github.com/shniu/notes/raw/master/img/eos-inline-action.jpg)

#### 3. eosio.token 合约的解读

[understanding-the-eosio-token-contract](https://medium.com/coinmonks/understanding-the-eosio-token-contract-87466b9fdca9)

深入浅出的讲解

#### 4. 去中心化的消息队列实现

[how-to-secure-messages-on-eos](https://medium.com/coinmonks/how-to-secure-messages-on-eos-ebb869a459ea)

You may be wondering what if I want to encrypt messages on blockchain, specifically, on EOS?

#### 5. eos 加密数据的例子

https://github.com/angelol/eos-encryption-demo

就是使用自己的公钥或者别人的公钥加密之后，放在链上，然后只能自己或者对方看到明文是什么，在 client 端就可以做了。

#### 6. eos 创建账号

https://github.com/angelol/smart_account_creator

将创建账号的操作放在智能合约里了，防止付钱之后不给创建账号的风险，去中心化的信任，合约里创建账号是非常值得学习的。
**这是一个很棒的例子。**

#### 7. eos 的一些例子：ipfs 和 mongo

https://github.com/angelol/eos-demos

- ipfs 的例子，将数据存储到ipfs，hash上链
- 将合约里的数据迁移到 mongo 中, 同样的项目还有 https://github.com/EOSpace/eos-sync，快速搭建非常方便

#### 8. eosameswaps

A decentralized, trustless, EOS account exchange dApp. 

https://github.com/StarryJapanNight/eosnameswaps

#### 9. eosio.forum

https://github.com/eoscanada/eosio.forum

### Interesting case

#### 1. DexChain

通证经济及去中心化交易链探索

- [黄皮书](https://github.com/DexChain/eos-dexchain/blob/master/YellowPaper.md)
- [Github](https://github.com/DexChain/eos-dexchain)

#### 2. eos-airdrop

- [eos-airdrop](https://github.com/itleaks/eos-airdrop)

A tool to airdrop token or issue a ad on EOS blockchain, EOS空投和广告工具

#### 3. https://github.com/angelol/simpleos  EOS Blockchain Interface & Wallet

SimplEOS is a wallet made solely for the EOS ecosystem and fully integrated with all features available in the EOS.IO software.

#### 4. eospy library

https://github.com/eosnewyork/eospy

#### 5. eosjs-cluster

https://github.com/DEXEOS/eosjs-cluster

eosjs-cluster can be used same way as using eosjs library. When there happens a problem with an endpoint, eosjs-cluster automatically calls the same request to the next endpoint.

### Analysis

#### 1. 在 EOS 区块链上开发我的第一个 dapp

- [在 EOS 区块链上开发我的第一个 dapp](https://bihu.com/article/1166771)

这篇文章能让你深入了解我是如何开发我的第一个 EOS dapp 以及一些简单的技巧来减少你的 EOS 智能合约所需的内存量

#### 2. REX

- [阉割掉SEOS后，REX如何工作](https://bihu.com/article/1145781)
- [EOS REX和SEOS深入剖析](https://bihu.com/article/1112584)
- [What REX Means for Token Holders](https://medium.com/@eoscafe/what-rex-means-for-token-holders-238375dea397)
- [Proposal for EOS Resource Renting & Rent Distribution](https://medium.com/@bytemaster/proposal-for-eos-resource-renting-rent-distribution-9afe8fb3883a)

