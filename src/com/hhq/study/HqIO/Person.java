package com.hhq.study.HqIO;

import java.io.Serializable;

/**
 * Created by macpro on 2017/7/24.
 */
//只有实现了Serializable接口的类的实例才可以被序列化
public class Person implements Serializable{

    //指定一个序列化的版本号
    private  static  final long serialVersionUID = 110;
    private  String name;
    private  int age;
    public  Person(){

    }
    public  Person(String name,int age){
        this.name = name;
        this.age  = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString(){

        return  "name ="+name+","+"age="+age;
    }
}
