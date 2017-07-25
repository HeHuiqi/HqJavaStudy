package com.hhq.study.HqThread;

/**
 * Created by macpro on 2017/7/25.
 */
public class HqDepositThread extends  Thread {
    private  Account account;
    //存钱数
    private  double depostiMoney;


    public  HqDepositThread(){}

    public  HqDepositThread(String name, Account account,double depostiMoney){
        super(name);
        this.account = account;
        this.depostiMoney = depostiMoney;
    }

    @Override
    public void run() {

        //执行30次存钱
        for (int i = 0;i<30;i++){

//            account.deposit(depostiMoney);
            account.myDeposit(depostiMoney);
        }


    }
}
