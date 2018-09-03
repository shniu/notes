
## C++ virtual

### Rules of Virtual Functions

- 必须在类的 public 部分声明
- Virtual Function 不能是静态的，并且不能使另一个类中的友元函数
- Virtual Function 需要使用基类的引用或指针访问来实现运行时多态
- Virtual Function 里的原型在基类和衍生类中保持一致
- 通常在基类中声明，在衍生类中重载。但是衍生类的重载不是必须的，如果不重载就使用基类的
- 类可以有 virtual 的析构函数，不能有virtual 的构造函数


## Ref

- [C++ Programming Language](https://www.geeksforgeeks.org/c-plus-plus/)
- [Virtual Function in C++](https://www.geeksforgeeks.org/virtual-function-cpp/)

