
## C++ command line

### Ref

- [Templatized C++ Command Line Parser Manual](http://tclap.sourceforge.net/manual.html)

TCLAP has a few key classes to be aware of. The first is the CmdLine (command line) class. This class parses the command line passed to it according to the arguments that it contains. Arguments are separate objects that are added to the CmdLine object one at a time. The six argument classes are: ValueArg, UnlabeledValueArg, SwitchArg, MultiSwitchArg, MultiArg and UnlabeledMultiArg. These classes are templatized, which means they can be defined to parse a value of any type. Once you add the arguments to the CmdLine object, it parses the command line and assigns the data it finds to the specific argument objects it contains. Your program accesses the values parsed by calls to the getValue() methods of the argument objects.
