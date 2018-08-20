
### Ref

- [Hyperledger Fabric创世纪块源码解析](https://blog.csdn.net/jiang_xinxing/article/details/54582149)
- [Hyperledger Fabric处理Peer与Peer之间通信的源码解析](https://blog.csdn.net/jiang_xinxing/article/details/54585452)
- [fabric源码解析12——peer的MSP服务](https://blog.csdn.net/idsuf698987/article/details/77103011)
- [fabric源码解析11——peer的Admin和Endorser服务](https://blog.csdn.net/idsuf698987/article/details/77044436)
- [Fabric的源码阅读方式](https://blog.csdn.net/u014732537/article/details/78157210)
- [gRPC学习笔记](https://blog.csdn.net/u014732537/article/details/78145331)
- [Protocol Buffers的学习笔记](https://blog.csdn.net/u014732537/article/details/78145322)
- [Hyperledger系列（十五）MSP图解](https://blog.csdn.net/maixia24/article/details/79932820)
- [Hyperledger系列（十四）向一个Channel中添加Org](https://blog.csdn.net/maixia24/article/details/79932783)
- [Hyperledger 系列文章](https://github.com/xiaofateng/knowledge-without-end/tree/master/Hyperledger)
- [区块链学习专栏序](https://zhuanlan.zhihu.com/p/32680313)
- [zhihu Hyperledger hot](https://www.zhihu.com/topic/20176768/hot)
- [深入理解Fabric环境搭建的详细过程](http://www.cnblogs.com/studyzy/p/7451276.html)
- [Fabric网络搭建过程之配置文件](https://zhuanlan.zhihu.com/p/35339234)
- [fabric源码解析10——文档翻译之Architecture](https://blog.csdn.net/idsuf698987/article/details/76780029)


### Hyperledger event

hyperledger v1.1中的事务处理是一个跨越多个组件（application，endorsing peer，order，committing peer）的长操作，需要花费相当长的时间（以秒为单位而不是毫秒）来完成。因此，应用程序必须设计成异步通知的方式来进行对事务生命周期的处理。在交易提案成功通过之后，在事务消息已成功广播给order之前，应用程序应该注册一个侦听器listener，以便在事务达到最终状态（当包含该事务的块被添加时到peer的区块链上）时进行通知。


fabric committing peers提供了一个事件流来将区块发布到已注册的侦听器。只要committing peers将验证区块添加到自己的账本上时，该块都会被发布给已经注册的监听器。fabric提供了三种注册侦听器来获得通知：

1：注册一个“块监听器”以便为每个区块事件调用。侦听器将传递一个完全解码的Block对象。请参阅registerBlockListener（）；

2：注册一个“事务监听器”，当id提交的特定事务被提交时（在已发布的块中发现）被调用。监听器将传递事务ID，事务状态和块号。请参阅registerTxListener（）；

3:注册一个“chaincode事件监听器”以在特定链代码事件到达时被调用。侦听器将传递ChaincodeEvent，块号，事务ID和事务状态。请参阅registerChaincodeEventListener（）；


但是这些事件通知是短暂的，如果注册的监听者在事件发布时崩溃，监听器就会错过该事件。下面有几种技术可以弥补由于客户端崩溃导致的错过事件：
1：注册块事件侦听器并记录接收到的块号，以便当下一个块到达并且其序号不是下一个序列时，则应用程序确切知道错过了哪些块事件。然后，它可以使用queryBlock从目标对端获取这些未命中的块，或使用startBlock选项注册事件以恢复或重放事件。如果您想停止收听，您还可以包含一个endBlock号码。
2：使用消息队列来捕获所有块事件。这样您将保证不会错过任何事件。结构事件监听器可以用任何编程语言编写。



MSP 是成员服务提供者，一个提供抽象化成员操作框架的组件；MSP将颁发与校验证书，以及用户认证背后的所有密码学机制与协议都抽象了出来。一个MSP可以自己定义身份，以及身份的管理（身份验证）与认证（生成与验证签名）规则。


### MSP

##### X.509

证书（certificate）是Fabric中权限管理的基础。目前采用了基于ECDSA算法的非对称加密算法来生成公钥和私钥，证书格式则采用了X.509的标准规范.

X.509 标准规定了证书可以包含什么信息，并说明了记录信息的方法;

证书的构成包括：

- 基本证书域（tbscertificate）：版本号、序列号、签名算法、颁发者、有效日期、主体、主体公钥信息、颁发者唯一标识（o）、扩展项（o）
- 签名算法域（signatureAlgorithm）:签名算法、参数（o）
- 签名值域（signatureValue）：签名值，签名值是使用签名算法对基本证书域（tbscertificate）全部信息签名后的值

##### MSP 证书生成

有 3 种方式：

- 根据配置文件使用 `cryptogen` 进行生成
- 使用 `Openssl`
- 使用 `Hyperledger Fabric CA`

##### peer 和 orderer MSP 配置

- admincerts：每个PEM文件对应于一个管理员证书
- cacerts：每个PEM文件对应于一个根CA的证书
- intermediatecerts：每个PEM文件对应于一个中间CA的证书
- config.yaml：包含相关OU的信息；其中Certificate表示通往（根或中间）CA的证书的相对路径，这些CA用于为组织成员发证（如./cacerts/cacert.pem）；OrganizationalUnitIdentifier表示预期会出现在X.509证书中的实际字符串（如“COP”）
- crls：包含相关CRL
- keystore：包含一个PEM文件及节点的签名密钥
- signcerts：包含一个PEM文件及节点的X.509证书
- tlscacerts：每个PEM文件对应于一个根TLS根CA的证书
- tlsintermediatecerts：每个PEM文件对应于一个中间TLS CA的证书

MSP 的验证参数：MSP标识符、信任证书的根、中间CA和管理员的证书，以及OU说明和CRL；

##### What is the difference between MSP and Fabric CA?

MSP is a Membership Service Provider - pluggable interface to support variety of credentials architectures, basically offering abstraction layer for membership orchestration architecture. MSP abstraction provides:

1. Concrete identity format
2. User credential validation
3. User credential revocation
4. Signature generation and verification

While Fabric-CA used to generate certificates and keys to actually initialize MSP facilities. Fabric-CA is a default implementation of MSP interface to cover identity management.

MSP 的目标是提供一个成员操作的基础架构的抽象；MSP 在颁布和验证证书以及更进一步的用户认证之上抽象出所有可能的协议加密机制。

NSP 正确运作的两个关键点：

- 成员身份被管理的规则（身份验证）
- 会员身份验证（签名生成和验证）

##### Question

- MSP 如何做实例化
- MSP 如何在 Channel 上做 Peer、Orderer、Client 等的身份验证和签名验证

1. 生成相关的证书和签名，这里的重点是根据设计好的网络结构与组织结构，配置证书生成文件，并生成 msp 证书
2. 在Peer，Orderer,Channel等组件的配置文件设置关于msp的相关信息；要想初始化一个MSP实例，首先，要生成用户权限管理和签名认证的证书。然后，每一个peer节点和orderer节点，Channel等都需要在本地指定其配置。
3. 在创世区块中会把每个MSP的关键验证参数放进去，如MSP标识符、信任证书的根、中间CA和管理员的证书，以及OU说明和CRL
4. Peer、Order节点启动的时候，就是对 MSP 做实例化的过程，因为会读取 mspId 与 mspConfigPath 来实例化 MSP
5. Channel 在创建的时候需要制定 Orderer 和 配置交易

![msp](https://i2.wp.com/www.blogsaays.com/wp-content/uploads/2017/10/blockchain-MSP-CA-sign.jpg)

- MSP 验证所有参与者的身份

##### MSP 目录结构

```
org1.example.com/
├── ca     # 存放组织Org1的根证书和对应的私钥文件，默认采用EC算法，证书为自签名。组织内的实体将基于该根证书作为证书根。
│   ├── ca.org1.example.com-cert.pem
│   └── dfb841b77804d726eea25231ae5e89a31901ca0538688a6d764731148f0bdc5b_sk
├── msp    # 存放代表该组织的身份信息。
│   ├── admincerts         # 组织管理员的身份验证证书，被根证书签名。
│   │   └── Admin@org1.example.com-cert.pem
│   ├── cacerts # 组织的根证书，同ca目录下文件。
│   │   └── ca.org1.example.com-cert.pem
│   └── tlscacerts          # 用于TLS的CA证书，自签名。
│       └── tlsca.org1.example.com-cert.pem
├── peers   # 存放属于该组织的所有Peer节点
│   ├── peer0.org1.example.com    # 第一个peer的信息，包括其msp证书和tls证书两类。
│   │   ├── msp # msp相关证书   
│   │   │   ├── admincerts  # 组织管理员的身份验证证书。Peer将基于这些证书来认证交易签署者是否为管理员身份。
│   │   │   │   └── Admin@org1.example.com-cert.pem
│   │   │   ├── cacerts     # 存放组织的根证书
│   │   │   │   └── ca.org1.example.com-cert.pem
│   │   │   ├── keystore    # 本节点的身份私钥，用来签名
│   │   │   │   └── 59be216646c0fb18c015c58d27bf40c3845907849b1f0671562041b8fd6e0da2_sk
│   │   │   ├── signcerts   # 验证本节点签名的证书，被组织根证书签名 
│   │   │   │   └── peer0.org1.example.com-cert.pem
│   │   │   └── tlscacerts  # TLS连接用到身份证书，即组织TLS证书
│   │   │       └── tlsca.org1.example.com-cert.pem
│   │   └── tls # tls相关证书
│   │       ├── ca.crt      # 组织的根证书
│   │       ├── server.crt  # 验证本节点签名的证书，被组织根证书签名
│   │       └── server.key  # 本节点的身份私钥，用来签名
│   └── peer1.org1.example.com    # 第二个peer的信息，结构类似。（此处省略。）
│       ├── msp
│       │   ├── admincerts
│       │   │   └── Admin@org1.example.com-cert.pem
│       │   ├── cacerts
│       │   │   └── ca.org1.example.com-cert.pem
│       │   ├── keystore
│       │   │   └── 82aa3f8f9178b0a83a14fdb1a4e1f944e63b72de8df1baeea36dddf1fe110800_sk
│       │   ├── signcerts
│       │   │   └── peer1.org1.example.com-cert.pem
│       │   └── tlscacerts
│       │       └── tlsca.org1.example.com-cert.pem
│       └── tls
│           ├── ca.crt
│           ├── server.crt
│           └── server.key
├── tlsca    # 存放tls相关的证书和私钥。
│   ├── 00e4666e5f56804274aadb07e2192db2f005a05f2f8fcfd8a1433bdb8ee6e3cf_sk
│   └── tlsca.org1.example.com-cert.pem
└── users    # 存放属于该组织的用户的实体
    ├── Admin@org1.example.com    # 管理员用户的信息，其中包括msp证书和tls证书两类。
    │   ├── msp # msp相关证书
    │   │   ├── admincerts     # 组织根证书作为管理员身份验证证书 
    │   │   │   └── Admin@org1.example.com-cert.pem
    │   │   ├── cacerts        # 存放组织的根证书
    │   │   │   └── ca.org1.example.com-cert.pem
    │   │   ├── keystore       # 本用户的身份私钥，用来签名
    │   │   │   └── fa719a7d19e7b04baebbe4fa3c659a91961a084f5e7b1020670be6adc6713aa7_sk
    │   │   ├── signcerts      # 管理员用户的身份验证证书，被组织根证书签名。要被某个Peer认可，则必须放到该Peer的msp/admincerts目录下
    │   │   │   └── Admin@org1.example.com-cert.pem
    │   │   └── tlscacerts     # TLS连接用的身份证书，即组织TLS证书
    │   │       └── tlsca.org1.example.com-cert.pem
    │   └── tls # 存放tls相关的证书和私钥。
    │       ├── ca.crt       # 组织的根证书
    │       ├── server.crt   # 管理员的用户身份验证证书，被组织根证书签名
    │       └── server.key   # 管理员用户的身份私钥，被组织根证书签名。
    └── User1@org1.example.com    # 第一个用户的信息，包括msp证书和tls证书两类
        ├── msp # msp证书相关信息
        │   ├── admincerts   # 组织根证书作为管理者身份验证证书。
        │   │   └── User1@org1.example.com-cert.pem
        │   ├── cacerts      # 存放组织的根证书
        │   │   └── ca.org1.example.com-cert.pem
        │   ├── keystore     # 本用户的身份私钥，用来签名
        │   │   └── 97f2b74ee080b9bf417a4060bfb737ce08bf33d0287cb3eef9b5be9707e3c3ed_sk
        │   ├── signcerts    # 验证本用户签名的身份证书，被组织根证书签名
        │   │   └── User1@org1.example.com-cert.pem
        │   └── tlscacerts   # TLS连接用的身份证书，被组织根证书签名。
        │       └── tlsca.org1.example.com-cert.pem
        └── tls # 组织的根证书
            ├── ca.crt       # 组织的根证书
            ├── server.crt   # 验证用户签名的身份证书，被根组织证书签名
            └── server.key   # 用户的身份私钥用来签名。
```

###### Fabric网络中，Member 与 用户的关系

> 在Fabric网络中，一个用户并不和一个Peer节点直接关联。
用户使用应用程序来和区块链网络进行交互，以完成交易。
该应用程序可以是APP或者WebAPP，它通过使用用户身份来代表用户行事。

> 例如，用户可以使用银行应用程序，该应用程序通过SDK与网络交互。应用程序会在网络上传递用户的身份;也就是说，用户所做的每一笔交易都将承担用户的身份和签名。
另一方面，应用程序可以代表用户行事，并使用其自己的身份和签名来签发交易。
用户是借助应用，连接Peer节点，而进入区块链网络进行操作的。

> HyperLedger Fabric中，成员和用户是两个不同的。成员是针对区块链网络中的组织，联盟等而言，而用户，是针对整个应用而言。用户只的是该系统给的使用者。

> Member 成员参与到区块链网络之中，拥有Peer节点或者Orderer节点 或运行在网络中的应用程序。
例如，多家银行可能会建立Fabric网络，因此他们是网络的成员，他们的客户可能是网络的用户。银行会部署Peer节点和Orderer节点来满足其客户的流量需求，但它不会是1个Peer对应1个用户。


##### 创世区块和配置区块

- 创始区块是指生成系统通道的创世区块，主要用于启动Ordering服务，配置网络中的策略

之所以叫创世区块我的理解是：这个block是用来启动orderer服务的，里面定义了网络的策略（也就是整个Blockchain network的策略），策略里定义了channel的创建、更新等管理策略，所以它来初始启动整个网络；在创建通道的时候，会根据策略定义来决定如何做，也就是说创建channel之前需要初始化网络配置

- Channel配置区块，主要用于新建应用通道，指定通道成员，以及访问策略等等
- 

### Ref

- [MSP 官方中文文档](https://hyperledgercn.github.io/hyperledgerDocs/msp_zh/)
- [RFC5280](https://tools.ietf.org/html/rfc5280)
- [X.509证书的解析、验证及使用](https://blog.csdn.net/liuxiaoxiaocsdn/article/details/78982976)
- [x.509数字证书编码详解](https://blog.csdn.net/kesay/article/details/46874699)
- [configure-msp-hyperledger-fabric-blockchain](https://www.blogsaays.com/configure-msp-hyperledger-fabric-blockchain/)
- [HyperLedger-Fabric原理-MSP详解（一）-MSP基础](https://zhuanlan.zhihu.com/p/35683522)
- [HyperLedger-Fabric原理-MSP详解（二）-Peer&Orderer配置MSP](https://zhuanlan.zhihu.com/p/35800948)
- [Hyperledger系列（十二）MSP详细介绍](https://blog.csdn.net/maixia24/article/details/79809759)
- [HyperLedger实战-手动搭建一个Fabric网络-基于Docker容器的方式](https://zhuanlan.zhihu.com/p/35363316)
