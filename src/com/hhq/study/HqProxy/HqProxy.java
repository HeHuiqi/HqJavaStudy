package com.hhq.study.HqProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface  Person{

    void  walk();
    void  sayHello(String name);

}

class HqInvocationHandler implements InvocationHandler
{

    //执行动态代理的所有方法都会替换成下面的invoke方法

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("正在执行的方法："+method);
        if (args == null){
            System.out.println("调用无参数的方法");
        }else {

            System.out.println("调用方法传入的参数如下：");
            for (Object arg : args){
                System.out.println("参数:"+arg);
            }
        }
        return null;
    }
}

public class HqProxy {

    public  static  void  main(String[] args){

        //创建InvocationHandler对象
        InvocationHandler hqInvocationHandler = new HqInvocationHandler();
        ClassLoader classLoader = Person.class.getClassLoader();
        Class[] interfaceClazz = new Class[]{Person.class};

        Person person = (Person) Proxy.newProxyInstance(classLoader,interfaceClazz,hqInvocationHandler);
        //调用动态代理的方法
        person.walk();
        person.sayHello("你好！");

    }


}
