
## Boost

这篇是关于 C++ 的 Boost 库，介绍一些常用的库和接口用法以及使用场景

### shared_ptr unique_ptr weak_ptr

[使用 boost 智能指针 shared_ptr](http://dengzuoheng.github.io/boost-shared-ptr)

[关于智能指针的一些分析](https://blog.csdn.net/jnu_simba/article/details/9569593)


### boost::bind

关于 [boost::bind](https://kelvinh.github.io/blog/2013/12/03/boost-bind-illustrated/)

- 可以用来将用户提供的需要一个参数的函数转换成不需要参数的函数对象
- 参数绑定也可以用于将类成员函数转换成零参数的函数对象

![boost-bind0](https://kelvinh.github.io/assets/blog/2013/12/03/boost-bind-illustrated/boost-bind0.png)
![boost-bind1](https://kelvinh.github.io/assets/blog/2013/12/03/boost-bind-illustrated/boost-bind1.png)

### boost::function

### boost::uuid

关于 [boost::uuid](https://theboostcpplibraries.com/boost.uuid)

provides generators for UUIDs.

```
#include <boost/uuid/uuid.hpp>
#include <boost/uuid/uuid_generators.hpp>
#include <boost/uuid/uuid_io.hpp>
#include <boost/lexical_cast.hpp>
#include <string>
#include <iostream>

using namespace boost::uuids;

int main()
{
  random_generator gen;
  uuid id = gen();

  std::string s = to_string(id);
  std::cout << s << '\n';

  std::cout << boost::lexical_cast<std::string>(id) << '\n';
}
```
