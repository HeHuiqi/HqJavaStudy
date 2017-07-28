package com.hhq.study.HqProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Dog{
   public void  info();
   public void  run();

}

class  RunDog implements Dog{

    //实现Dog接口的方法
   public void info(){
       System.out.println("一只会跑的🐶");
   }

   public void  run(){
       System.out.println("正在飞奔的狗。。。");
   }

}

class  DogUtil{

    public void method1(){
        System.out.println("----第一个通用方法----");
    }
    public void method2(){
        System.out.println("----第二个通用方法----\n");
    }
}

class  MyInvocationHandler implements InvocationHandler{

    //需要被代理的对象
    private  Object target;
    public  void  setTarget(Object target){
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }


    //执行动态代理的对象的所有方法都会替换执行如下的方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        DogUtil dogUtil = new DogUtil();
        //执行通用方法1
        dogUtil.method1();

        //以target来执行方法

        Object result = method.invoke(target,args);

        //执行通用方法2
        dogUtil.method2();


        return null;
    }
}

class MyProxyFactory{

    public  static  Object getProxy(Object target){

        MyInvocationHandler invocationHandler = new MyInvocationHandler();

        invocationHandler.setTarget(target);

        ClassLoader classLoader = target.getClass().getClassLoader();
        Class[] targetInterfaces = target.getClass().getInterfaces();

        return Proxy.newProxyInstance(classLoader,targetInterfaces,invocationHandler);


    }
}
public class HqProxyPro {

    public  static void  main(String[] args){

        Dog target = new RunDog();

        //以指定的target来创建动态代理
        Dog dog = (Dog)MyProxyFactory.getProxy(target);
        dog.info();
        dog.run();


    }

}
