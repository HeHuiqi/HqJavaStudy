package com.hhq.study.HqThread;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class  HqTaskThread implements  Runnable{


    @Override
    public void run() {

        for (int i =0;i<20;i++){

            System.out.println(Thread.currentThread().getName()+"="+i);
        }

    }
}

public class HqThreadPool {


    public void createThreadPool(){

        //创建一个包含10个线程的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        Thread t1 = new Thread(new HqTaskThread(),"task1");
        Thread t2 = new Thread(new HqTaskThread(),"task2");
        //提交线程,线程池会自动执行run方法
        service.submit(t1);
        service.submit(t2);

        //关闭线程池
        service.shutdown();

    }


}
