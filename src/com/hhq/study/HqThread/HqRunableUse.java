package com.hhq.study.HqThread;

/**
 * Created by macpro on 2017/7/25.
 */
public class HqRunableUse implements  Runnable{

    private int i;
    @Override
    public void run() {

        for (;i<100;i++){

            //实现Runnable的接口的类只能通过如下方法获取线程名
            System.out.println( Thread.currentThread().getName()+" "+i);
        }
    }
}



