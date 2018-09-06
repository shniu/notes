
## C++ command line

### Ref

- [Templatized C++ Command Line Parser Manual](http://tclap.sourceforge.net/manual.html)

TCLAP has a few key classes to be aware of. The first is the CmdLine (command line) class. This class parses the command line passed to it according to the arguments that it contains. Arguments are separate objects that are added to the CmdLine object one at a time. The six argument classes are: ValueArg, UnlabeledValueArg, SwitchArg, MultiSwitchArg, MultiArg and UnlabeledMultiArg. These classes are templatized, which means they can be defined to parse a value of any type. Once you add the arguments to the CmdLine object, it parses the command line and assigns the data it finds to the specific argument objects it contains. Your program accesses the values parsed by calls to the getValue() methods of the argument objects.

- [CLIUtils CLI11](https://github.com/CLIUtils/CLI11)

CLI11 is a command line parser for C++11 and beyond that provides a rich feature set with a simple and intuitive interface. https://cliutils.gitlab.io/CLI11Tutorial

- [Lightweight C++ command line option parser](https://github.com/jarro2783/cxxopts)

This is a lightweight C++ option parser library, supporting the standard GNU style syntax for options.
