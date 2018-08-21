
## C++ Socket 编程

### 1. 一般步骤

- 服务器端编程的步骤：

1：加载套接字库，创建套接字(WSAStartup()/socket())；
2：绑定套接字到一个IP地址和一个端口上(bind())；
3：将套接字设置为监听模式等待连接请求(listen())；
4：请求到来后，接受连接请求，返回一个新的对应于此次连接的套接字(accept())；
5：用返回的套接字和客户端进行通信(send()/recv())；
6：返回，等待另一连接请求；
7：关闭套接字，关闭加载的套接字库(closesocket()/WSACleanup())。

```
1. create a socket - Get the file descriptor!
2. bind to an address -What port am I on?
3. listen on a port, and wait for a connection to be established.
4. accept the connection from a client.
5. send/recv - the same way we read and write for a file.
6. shutdown to end read/write.
7. close to releases data.
```

- 客户端编程的步骤：

1：加载套接字库，创建套接字(WSAStartup()/socket())；
2：向服务器发出连接请求(connect())；
3：和服务器端进行通信(send()/recv())；
4：关闭套接字，关闭加载的套接字库(closesocket()/WSACleanup())。

```
1. create a socket.
2. bind* - this is probably be unnecessary because you're the client, not the server.
3. connect to a server.
4. send/recv - repeat until we have or receive data
5. shutdown to end read/write.
6. close to releases data.
```


### Socket

- [x] Socket
- [ ] 文件

Socket 是应用层与 TCP/IP 协议族通信的中间软件抽象层，它是一组接口，是计算机之间进行通信的一种约定或一种方式。

它是使用标准Unix 文件描述符 (file descriptor) 和其它程序通讯的方式。Unix 程序在执行任何形式的 I/O 的时候，程序是在读或者写一个文件描述符。一个文件描述符只是一个和打开的文件相关联的整数。这个文件可能是一个网络连接，FIFO，管道，终端，磁盘上的文件或者什么其它的东西。Unix 中所有的东西就是文件！那如何才能获得文件描述符呢？那就是利用系统调用 socket()，它返回套接字描述符 (socket descriptor)，然后你再通过它来进行send() 和 recv()调用。

![TCP conn](http://www.bogotobogo.com/cplusplus/images/socket/TCP_IP_socket_diagram.png)

##### 数据传输方式

- SOCK_STREAM 表示面向连接的数据传输方式。数据可以准确无误地到达另一台计算机，如果损坏或丢失，可以重新发送，但效率相对较慢。常见的 http 协议就使用 SOCK_STREAM 传输数据，因为要确保数据的正确性，否则网页不能正常解析。

- SOCK_DGRAM 表示无连接的数据传输方式。QQ 视频聊天和语音聊天就使用 SOCK_DGRAM 传输数据，因为首先要保证通信的效率，尽量减小延迟，而数据的正确性是次要的，即使丢失很小的一部分数据，视频和音频也可以正常解析，最多出现噪点或杂音，不会对通信质量有实质的影响。

##### 结构体

```cpp
struct sockaddr {

    // 地址家族, AF_xxx
　　unsigned short sa_family;
    // 14字节协议地址
　　char sa_data[14];
};

struct sockaddr_in {

　　short int sin_family; /* 通信类型 */
　　unsigned short int sin_port; /* 端口 */
　　struct in_addr sin_addr; /* Internet 地址 */
　　unsigned char sin_zero[8]; /* 与sockaddr结构的长度相同*/
};
```

##### socket()

```cpp
#include <sys/types.h>
#include <sys/socket.h>

int socket(int domain, int type, int protocol);
```

这是一个系统调用, 返回一个 Socket 描述符。

- domain: set `AF_INET`
- type: `SOCK_STREAM` or `SOCK_DGRAM`
- protocol: 0
- Return Socket 描述符，一个 `unsinged int`

##### bind()

```cpp
#include <sys/types.h>
#include <sys/socket.h>

int bind(int sockfd, struct sockaddr *my_addr, int addrlen);
```

- sockfd: 调用 socket 返回的文件描述符
- my_addr: 指向数据结构 struct sockaddr 的指针
- addrlen: sizeof(struct sockaddr)

```cpp
// demo
struct sockaddr_in my_addr;
int sockfd;
sockfd = socket(AF_INET, SOCK_STREAM, 0);
my_addr.sin_family = AF_INET;  // host byte order
my_addr.sin_port = htons(MYPORT);  // short, network byte order
my_addr.sin_addr.s_addr = inet_addr("132.241.5.10");   // 点分十进制表示的ip地址转成 long
bzero(&(my_addr.sin_zero), 8);   // bzero 对指定内存的置零
bind(sock_fd, (struct sockaddr *)&my_addr, sizeof(struct sockaddr)) 

// --
my_addr.sin_port = htons(0); /* 随机选择一个没有使用的端口 */
my_addr.sin_addr.s_addr = htonl(INADDR_ANY); /* 使用自己的IP地址 */
```

##### listen()

```cpp
int listen(int sockfd, int backlog);
```

- sockfd: 调用 socket() 返回的套接字文件描述符
- backlog: 在进入 队列中允许的连接数目, 大多数系统的允许数目是20

##### accept()

```cpp
#include <sys/socket.h>

int accept(int sockfd, void *addr, int *addrlen);
```

##### send() and recv()

```cpp
int recv(int sockfd, void *buf, int len, unsigned int flags);
```

### 一个完整点的例子

```cpp
// client.cpp
#include <cstdio>
#include <cstdlib>
#include <cerrno>
#include <netdb.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <unistd.h>
#include <arpa/inet.h>
#define MAXDATASIZE 1000

using namespace std;

int main(int argc, char** argv) {
    if (argc < 5)
        printf("Input format error\n");
        return 0;
    }

    in_addr_t server_ip = inet_addr(argv[1]);
    in_port_t server_port = atoi(argv[2]);
    in_addr_t my_ip = inet_addr(argv[3]);
    in_port_t my_port = atoi(argv[4]);
}

// server.cpp
#include <cstdio>
#include <cstdlib>
#include <cerrno>
#include <cstring>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/wait.h> 
#include <arpa/inet.h>
#include <unistd.h>
#define SERVPORT 3333   // 服务器监听端口号
#define BACKLOG 10  // 最大同时连接请求数
#define MAXDATASIZE 1000

int main(int argc, char** argv) {
    int sock_fd;
    struct sockaddr_in my_addr;

    if ((sock_fd = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        printf("new socket error");
        exit(1);
    }

    int flag = 1;
    setsockopt(sock_fd, SOL_SOCKET, SO_REUSEADDR, (char *)&flag, sizeof(flag));
    my_addr.sin_family = AF_INET;
    my_addr.sin_port = htons(SERVPORT);
    my_addr.sin_addr.s_addr = INADDR_ANY;
    bzero(&(my_addr.sin_zero), 8);
    if (bind(sock_fd, (struct sockaddr *)&my_addr, sizeof(struct sockaddr)) == -1) {
        printf("bind socket error");
        exit(1);
    }

    if (listen(sock_fd, BACKLOG) == -1) {
        printf("listen socket error");
        exit(1);
    }

    // client socket info
    int client_fd;
    struct sockaddr_in remote_addr;
    while (1) {
        int sin_size = sizeof(struct sockaddr_in);
        if ((client_fd = accept(sock_fd, (struct sockaddr *)&remote_addr, (socklen_t*)&sin_size)) == -1) {
            printf("accpet socket error");
            continue;
        }

        printf("received a connection from %s:%u\n", inet_ntoa(remote_addr.sin_addr), ntohs(remote_addr.sin_port));

        // fork

        close(client_fd);
    }
}
```

### 延伸阅读

- [SOCKETS - SERVER & CLIENT - 2018 C++](http://www.bogotobogo.com/cplusplus/sockets_server_client.php)

对 Scoket 编程做了深入的总结，包括：TCP, UDP, Boost.Asio, Socket 的底层接口, Blocking socket and non-blocking socket等
