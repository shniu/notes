> 资源虽好，唯有认真学习反复思考不断实践，方可为己所用。

## Front end awesome

共享前端的一些优质资源。

### Javascript 核心原理

- [Javascript 语言精粹](https://book.douban.com/subject/11874748/)

介绍 Javascript 语言本质的书，只得反复阅读、深入学习、深入理解。这本书应该是学习 JS 的开始。

- [Effective JavaScript](https://book.douban.com/subject/25786138/)

这本书深刻辨析JavaScript 的内部运作机制、特性、陷阱和编程最佳实践。有深度，可以让我们更好的掌握 JS。

- ES6 的东西，看看一峰翻译的 [ES6 教程](http://es6.ruanyifeng.com/)就可以

- [ES6 tools](https://github.com/addyosmani/es6-tools)

- [You don't know js 系列](https://github.com/addyosmani/es6-tools)

  - "Up & Going"
  - "Scope & Closures"
  - "this & Object Prototypes"
  - "Types & Grammar"
  - "Async & Performance"
  - "ES6 & Beyond"
  
- [How Javascript works 系列文章](https://blog.sessionstack.com/@zlatkov)

  - [How JavaScript works: an overview of the engine, the runtime, and the call stack](https://blog.sessionstack.com/how-does-javascript-actually-work-part-1-b0bacc073cf)
  这篇文章大致介绍了 Javascript 是如何运行的；我们很早就听说 V8 引擎的概念，大多数人都知道 Javascript 是单线程并且使用回调，但是没有深入了解过，这边文章就带你一探究竟。
  
  - [How JavaScript works: inside the V8 engine + 5 tips on how to write optimized code](https://blog.sessionstack.com/how-javascript-works-inside-the-v8-engine-5-tips-on-how-to-write-optimized-code-ac089e62b12e)
  了解 V8 引擎。
  
  - [Event loop and the rise of Async programming + 5 ways to better coding with async/await](https://blog.sessionstack.com/how-javascript-works-event-loop-and-the-rise-of-async-programming-5-ways-to-better-coding-with-2f077c4438b5)
  Event loop 和异步编程。
  
  - and so on，还有很多，可以在专栏里去探索
  
### 浏览器原理

- [浏览器是如何工作的](http://taligarsiel.com/Projects/howbrowserswork1.htm)

讲述的比较详细，包括渲染引擎，布局等等核心技术。

- [浏览器的渲染原理简介](https://coolshell.cn/articles/9666.html)

#### Virtual DOM

virtual dom 是 React 的一个核心技术

- [深度剖析：如何实现一个 Virtual DOM 算法](https://github.com/livoras/blog/issues/13)

这篇写的很不错，感觉看完搞懂基本不用看其他的了。

- [How to write your own Virtual DOM](https://medium.com/@deathmood/how-to-write-your-own-virtual-dom-ee74acc13060)
- [Write your Virtual DOM 2: Props & Events](https://medium.com/@deathmood/write-your-virtual-dom-2-props-events-a957608f5c76)

### 网络协议

- [Web性能权威指南](https://book.douban.com/subject/25856314/)

不错的一本书，全书以性能优化为主线，从TCP、UDP 和TLS 协议讲起，解释了如何针对这几种协议和基础设施来优化应用。然后深入探讨了无线和移动网络的工作机制。最后，揭示了HTTP 协议的底层细节，同时详细介绍了HTTP 2.0、 XHR、SSE、WebSocket、WebRTC 和DataChannel 等现代浏览器新增的具有革命性的新能力。

- [http2 详解](https://ye11ow.gitbooks.io/http2-explained/content/)

HTTP 2.0 是未来的发展方向，一定要去了解一下。

- 还有这本 [http 2 协议白皮书](https://www.nginx.com/wp-content/uploads/2015/09/NGINX_HTTP2_White_Paper_v4.pdf)

- [HTML5 WebSocket: A Quantum Leap in Scalability for the Web](http://www.websocket.org/quantum.html)

这篇文章对比分析了集中供暖连接方式，Polling、Long Polling 和 Streaming，并引入了终极方案 Websocket。

- [An Introduction to WebSockets](http://blog.teamtreehouse.com/an-introduction-to-websockets)

Websocket 的简单教程

### 一些很棒的类库

- [y.js](https://github.com/y-js/yjs)

A framework for real-time p2p shared editing on any data. p2p js 库
