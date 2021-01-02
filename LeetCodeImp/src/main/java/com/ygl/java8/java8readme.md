lambda的本质就是功能性接口中方法的一种实现，替代java7中老有的new Object(parameter)这种方式，直接用 parameter -> logic of method的这种方式。

    jdk7：接口只有常量和抽象方法，无构造器
    jdk8：接口增加了 默认方法 和 静态方法，无构造器
    jdk9：接口允许 以 private 修饰的方法，无构造器