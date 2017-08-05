package com.hhq.study.HqThread;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by macpro on 2017/7/25.
 */
public class HqThreadUseTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public  void  testHqThread(){



        for (int i = 0;i<20;i++){
            System.out.println("currentThreadName=="+Thread.currentThread().getName()+" "+i);

            if (i == 10){

                HqThreadUse t1 = new HqThreadUse();
                t1.start();
                //join()方法会让一个其他线程等待，直到被join的线程执行完才开始执行
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //调用start()方法启动线程将执行run方法

//        创建第二个线程
        HqThreadUse t2 = new HqThreadUse();
        //设置为后台线程，当前台线程都死亡，后台线程也会死亡
        t2.setDaemon(true);
        //设置线程优先级1-10
        t2.setPriority(10);
        t2.start();
    }

    @Test
    public  void  testHqRunable(){

        HqRunableUse st = new HqRunableUse();
//        实现Runable接口的类只能作为线程的target来创建线程
        new Thread(st,"线程1").start();
        new Thread(st,"线程2").start();
    }

    @Test
    public  void  testGetBalance(){

        Account account = new Account("612499898",1000);
        new HqGetBalnaceThread("甲",account,800).start();
        new HqGetBalnaceThread("已",account,1000).start();

    }

    @Test
    public  void  testDrawAndDepositMoney(){

        Account myAccount = new Account("1234567",0);
        new HqDrawThread("取钱者：",myAccount,1000).start();
        new HqDepositThread("存钱者甲：",myAccount,1000).start();
        new HqDepositThread("存钱者乙：",myAccount,1000).start();
        new HqDepositThread("存钱者丙：",myAccount,1000).start();

    }

    @Test

    public void  testThreadPool(){

        new  HqThreadPool().createThreadPool();
    }

}