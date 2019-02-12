
### Blog

- [深入理解 Go Channel](http://legendtkl.com/2017/07/30/understanding-golang-channel/) 

从CSP模型说起，一点点深入讲解 Go 中的 Channel

```
ch := make(chan int, 10)

// 读操作
x <- ch

// 写操作
ch <- x

// 关闭
close(ch)
// 重复关闭 channel 会导致 panic。
// 向关闭的 channel 发送数据会 panic。
// 从关闭的 channel 读数据不会 panic，读出 channel 中已有的数据之后再读就是 channel 类似的默认值，比如 chan int 类型的 channel 关闭之后读取到的值为 0。

// Channel 分为无缓冲 Channel 和有缓冲 Channel
```

- [Go Channel 源码剖析](http://legendtkl.com/2017/08/06/golang-channel-implement/)

// Todo
