
### X-Y Problem

意思如下:

```
1）有人想解决问题X
2）他觉得Y可能是解决X问题的方法
3）但是他不知道Y应该怎么做
4）于是他去问别人Y应该怎么做？

简而言之，没有去问怎么解决问题X，而是去问解决方案Y应该怎么去实现和操作。
```
于是乎：

1）热心的人们帮助并告诉这个人Y应该怎么搞，但是大家都觉得Y这个方案有点怪异。
2）在经过大量地讨论和浪费了大量的时间后，热心的人终于明白了原始的问题X是怎么一回事。
3）于是大家都发现，Y根本就不是用来解决X的合适的方案。

X-Y Problem最大的严重的问题就是：在一个根本错误的方向上浪费他人大量的时间和精力！

例子1：

```
Q) 我怎么用Shell取得一个字符串的后3位字符？
A1) 如果这个字符的变量是$foo，你可以这样来 echo ${foo:-3}
A2) 为什么你要取后3位？你想干什么？
Q) 其实我就想取文件的扩展名
A1) 我靠，原来你要干这事，那我的方法不对，文件的扩展名并不保证一定有3位啊。
A1) 如果你的文件必然有扩展名的话，你可以这来样来：echo ${foo##*.}
```

例子2：
```
Q）问一下大家，我如何得到一个文件的大小
A1)  size = `ls -l $file  | awk ‘{print $5}’`
Q) 哦，要是这个文件名是个目录呢？
A2) 用du吧
A3) 不好意思，你到底是要文件的大小还是目录的大小？你到底要干什么？
Q)  我想把一个目录下的每个文件的每个块（第一个块有512个字节）拿出来做md5，并且计算他们的大小 ……
A1) 哦，你可以使用dd吧。
A2) dd不行吧。
A3) 你用md5来计算这些块的目的是什么？你究竟想干什么啊？
Q) 其实，我想写一个网盘，对于小文件就直接传输了，对于大文件我想分块做增量同步。
A2) 用rsync啊，你妹！
```

其他一些变种：

```
其一、大多数人有时候，非常容易把手段当目的，他们会用自己所喜欢的技术和方法来反推用户的需求，于是很有可能就会出现X-Y Problem – 也许解决用户需求最适合的技术方案是PC，但是我们要让他们用手机。

其二、产品经理有时候并不清楚他想解决的用户需求是什么，于是他觉得可能开发Y的功能能够满足用户，于是他提出了Y的需求让技术人员去做，但那根本不是解决X问题的最佳方案。

其三、因为公司或部门的一些战略安排，业务部门设计了相关的业务规划，然后这些业务规划更多的是公司想要的Y，而不是解决用户的X问题。

其四、对于个人的职业发展，X是成长为有更强的技能和能力，这个可以拥有比别人更强的竞争力，从而可以有更好的报酬，但确走向了Y：全身心地追逐KPI。

其五、本来我们想达成的X是做出更好和更有价值的产品，但最终走到了Y：通过各种手段提升安装量，点击量，在线量，用户量来衡量。

其六、很多团队Leader都喜欢制造信息不平等，并不告诉团队某个事情的来由，掩盖X，而直接把要做的Y告诉团队，导致团队并不真正地理解，而产生了很多时间和经历的浪费。
```

> 所以在遇到问题的时候，我们不要过早下结论，先去自己找到最真实要解决的问题，努力去想问题的解决办法，在提问时，一定要先说最核心的正确的要解决的问题是什么，然后可以将自己调研过的几种方案都简要陈述一下，然后去求助他人。

> To avoid falling into this trap, always include information about a broader picture along with any attempted solution. If someone asks for more information, or especially a more specific question, do provide details. If there are other solutions which you believe will be suggested and which you've already ruled out, then don't try to avoid going over them again – instead state why you've ruled them out, as this gives more information about your requirements and helps others provide better answers.

---

Ref: https://coolshell.cn/articles/10804.html
Ref: http://xyproblem.info/
Ref: http://mywiki.wooledge.org/XyProblem
Ref: https://meta.stackexchange.com/questions/66377/what-is-the-xy-problem

