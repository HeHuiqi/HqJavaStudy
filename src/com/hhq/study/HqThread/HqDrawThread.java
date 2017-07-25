package com.hhq.study.HqThread;

/**
 * Created by macpro on 2017/7/25.
 */
public class HqDrawThread extends  Thread{

    private  Account account;
    private  double drawAmount;


    public  HqDrawThread(){}

    public  HqDrawThread(String name, Account account,double drawAmount){
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {

    //执行30次取钱
        for (int i = 0;i<30;i++){

//            account.draw(drawAmount);
            account.mydraw(drawAmount);
        }


    }
}
