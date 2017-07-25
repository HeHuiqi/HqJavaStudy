package com.hhq.study.HqThread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by macpro on 2017/7/25.
 */
public class HqGetBalnaceThread extends  Thread {

    private  Account account;
    private  double getMoney;

    private static final ReentrantLock lock = new ReentrantLock();

    public  HqGetBalnaceThread(){}

    public  HqGetBalnaceThread(String name, Account account,double getMoney){
        super(name);
        this.account = account;
        this.getMoney = getMoney;
    }

    @Override
    public void run() {


        //3.使用lock来保证线程安全
        lock.lock();
        try {
            account.getMyMoney(getMoney);

        }finally {
            lock.unlock();
        }


        //2.调用同步方法保证线程安全
//        account.getMoney(getMoney);


        //1.使用线程同步块保证线程安全
//        synchronized (account){
//           account.getMyMoney(getMoney);
//        }

    }
}
