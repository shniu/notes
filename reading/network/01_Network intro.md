
## Network

### Issue


- 当网络包到达一个网关时，可以通过路由表找到下一个网关的IP地址，直接通过IP地址找就可以了，为什么还要通过本地的MAC地址呢？

简单的回答：MAC地址是工作在数据链路层的，而IP是工作在网络层，职能和履行的义务不同；首先 MAC 地址是固化在硬件里的，可以全球唯一的表示一个网卡，而IP地址是动态绑定的，是分配给网卡临时使用的，所以在通信过程中，链路层的数据包需要比对MAC地址是否一致来决定这个包是否是发给自己的，自己需不需要去处理；打个非常好理解的比方，MAC 地址相当于我们每个人出生时的身份证号，每个人的都是独一无二的，当我们需要发个快递给某个人，我们无法通过身份证号来找到这个人，因为这个人有可能在世界任何一个地方，所以我们在邮寄快递的时候需要写上收件人的地址，通过地址就可以找到那个人，怎么确定就是那个人呢，就用身份证号来匹配一下，所以类比一下，MAC 地址 => 身份证号，IP 地址 => 能找到我们的一个地址，如公司地址，现住址。

### 延伸阅读

- [Concurrent Servers 系列](https://eli.thegreenplace.net/2017/concurrent-servers-part-1-introduction/)

- [Let's code a TCP/IP stack](http://www.saminiir.com/lets-code-tcp-ip-stack-1-ethernet-arp/)

- [Computer Network Tutorials](https://www.geeksforgeeks.org/computer-network-tutorials/)

geeksforgeeks.org 出品的计算机网络基础手册。

- [Beej's Guide to Network Programming Using Internet Sockets](http://beej.us/guide/bgnet/html/multi/index.html)

- [Learn Socket Programming in C from Scratch](https://www.udemy.com/learn-socket-programming-in-c-from-scratch/)

Master the socket programming concepts and start building networked applications in C programming language
