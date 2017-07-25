package com.hhq.study.HqThread;

/**
 * Created by macpro on 2017/7/25.
 */
public class HqThreadUse extends  Thread{
    private  int i;

    @Override
    public void  run(){
       for (;i<20;i++){

           //getName()方法获取线程名
           System.out.println(getName()+ " "+i);
       }
    }
}
