package com.digcredit.shniu.principle.isp;

/**
 * Created by shniu on 2017/3/15.
 */

interface I {
    public void method1();
    public void method2();
    public void method3();
    public void method4();
    public void method5();
}

class A{
    public void depend1(I i){
        i.method1();
    }
    public void depend2(I i){
        i.method2();
    }
    public void depend3(I i){
        i.method3();
    }
}

class B implements I{
    public void method1() {
        System.out.println("类B实现接口I的方法1");
    }
    public void method2() {
        System.out.println("类B实现接口I的方法2");
    }
    public void method3() {
        System.out.println("类B实现接口I的方法3");
    }
    //对于类B来说，method4和method5不是必需的，但是由于接口A中有这两个方法，
    //所以在实现过程中即使这两个方法的方法体为空，也要将这两个没有作用的方法进行实现。
    public void method4() {}
    public void method5() {}
}

class C{
    public void depend1(I i){
        i.method1();
    }
    public void depend2(I i){
        i.method4();
    }
    public void depend3(I i){
        i.method5();
    }
}

class D implements I{
    public void method1() {
        System.out.println("类D实现接口I的方法1");
    }
    //对于类D来说，method2和method3不是必需的，但是由于接口A中有这两个方法，
    //所以在实现过程中即使这两个方法的方法体为空，也要将这两个没有作用的方法进行实现。
    public void method2() {}
    public void method3() {}

    public void method4() {
        System.out.println("类D实现接口I的方法4");
    }
    public void method5() {
        System.out.println("类D实现接口I的方法5");
    }
}

public class Bad {

    public static void main(String[] args) {
        A a = new A();
        a.depend1(new B());
        a.depend2(new B());
        a.depend3(new B());

        C c = new C();
        c.depend1(new D());
        c.depend2(new D());
        c.depend3(new D());
    }
}
