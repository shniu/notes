
## 并发编程

### 并发介绍

#### 为什么使用并发

- 为了关注点分离

通过将相关的代码与无关的代码分离，可以使程序更容易理解和测试，从而减少出错的可能性。

- 性能

任务并行和数据并行

#### 入门Demo

```cpp
#include <iostream>
#include <thread> 
void hello()  
{
  std::cout << "Hello Concurrent World\n";
}
int main()
{
  std::thread t(hello);  
  t.join();  
}
```


## Ref

- [cpp_concurrency_in_action](https://chenxiaowei.gitbooks.io/cpp_concurrency_in_action/content/)

本书是基于C++11新标准的并发和多线程编程深度指南

- [Concurrent Servers: Part 1 - Introduction](https://eli.thegreenplace.net/2017/concurrent-servers-part-1-introduction/)
