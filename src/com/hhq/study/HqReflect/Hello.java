package com.hhq.study.HqReflect;

public class Hello {

    public  static  void  main(String[] args){

        System.out.println("Hello");
        for (String arg:
                args ) {
            System.out.println("运行时的参数:"+arg);
        }
    }
}
