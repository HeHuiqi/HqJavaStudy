package com.hhq.study.HqThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by macpro on 2017/7/25.
 */
public class Account {
    private  String accountNo;
    private  double balance;

    //定义一个lock
    private  static  final ReentrantLock lock = new ReentrantLock();
    //获取条件指定lock条件变量对象
    private  static  final Condition condition = lock.newCondition();

    //表示账户是否有钱可取
    private  boolean flag;
    public  Account(){}
    public  Account(String accountNo,double balance){
        this.accountNo = accountNo;
        this.balance = balance;
    }


    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        return accountNo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj.getClass()==Account.class){

            return true;
        }
        return false;
    }

    //使用synchronized修饰方法提供一个线程安全的方法来取钱
    public  synchronized void getMoney(double amount){

        getMyMoney(amount);
    }

    //使用synchronized修饰方法提供一个线程安全的方法来取钱
    public  void getMyMoney(double amount){

        if (balance >= amount){

            System.out.println(Thread.currentThread().getName()+ "取钱成功，突出钞票:"+amount);

            try {

                Thread.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }
            balance -=amount;

            System.out.println("\t"+"余额为："+balance);


        }else {

            System.out.println(Thread.currentThread().getName()+"取钱失败,余额不足!");
        }
    }


    /*
    //使用synchronized修饰方法会有隐式的同步监视器对象，这样才可以直接调用
    //wait() notify()和notifyAll()方法
    //1.取钱
    public synchronized void  draw(double drawAmonut){

        try {
            if (!flag){
                //没钱等待
                wait();
            }else {

                //有钱就取
                System.out.println(Thread.currentThread().getName() + "取钱："+drawAmonut);
                balance -= drawAmonut;
                System.out.println("账户余额为："+balance);
                //设置已取完钱，没钱了
                flag = false;
                //唤醒其他线程
                notifyAll();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //1.存钱
    public  synchronized void  deposit(double depositAmount){

        try {

            if (flag){
                //如果有钱就等，不存
                wait();
            }else{

                //没钱就存钱
                System.out.println(Thread.currentThread().getName()+ "存款："+depositAmount);
                balance += depositAmount;
                System.out.println("账户余额为："+balance);
                //修改标志有钱可取
                flag = true;
                //唤醒线程
                notifyAll();
            }
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    */

    //使用条件变量的方式来控制线程同步,普通方法的同步调用wait等是没有作用的
    //调用将会出现异常
    //此时应该用Condition对象来实现同步
    //2.取钱
    public  void  mydraw(double drawAmonut){

        //要配合lock来使用
        lock.lock();
        try {
            if (!flag){
                //没钱等待,，注意是await()
                condition.await();
            }else {

                //有钱就取
                System.out.println(Thread.currentThread().getName() + "取钱："+drawAmonut);
                balance -= drawAmonut;
                System.out.println("账户余额为："+balance);
                //设置已取完钱，没钱了
                flag = false;
                //唤醒其他线程
                condition.signalAll();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            System.out.println("取款解锁");
            lock.unlock();
        }
    }

    //2.存钱
    public   void  myDeposit(double depositAmount){

        lock.lock();
        try {

            if (flag){
                //如果有钱就等，不存，注意是await()
                condition.await();
            }else{

                //没钱就存钱
                System.out.println(Thread.currentThread().getName()+ "存款："+depositAmount);
                balance += depositAmount;
                System.out.println("账户余额为："+balance);
                //修改标志有钱可取
                flag = true;
                //唤醒线程
                condition.signalAll();
            }
        }catch (Exception e){

            e.printStackTrace();
        }finally {
//            System.out.println("存款解锁");
            lock.unlock();
        }
    }
}
