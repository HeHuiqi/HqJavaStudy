package com.hhq.study.HqNetWork;

import com.hhq.study.HqThread.HqDrawThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * Created by macpro on 2017/7/25.
 */
public class HqDownThread extends Thread{
    //定义一个

    private  final int BUFF_LEN = 100;
    //起点
    private  long start;
    //结束点
    private  long end;
    //现在的输入流
    private InputStream is;
    //写到文件中
    private RandomAccessFile raf;

    public HqDownThread(){}

    public  HqDownThread(String name, long start, long end ,InputStream is,RandomAccessFile raf){

        super(name);

        System.out.println("T-name="+name);
        System.out.println(start+"----->"+end);
        this.start = start;
        this.end = end;
        this.is = is;
        this.raf = raf;
    }
    @Override
    public  void  run(){
        try {


            System.out.println("run="+getName());

            is.skip(start);
            raf.seek(start);

            byte[] buff = new byte[BUFF_LEN];
            int hasRead = 0;

            while ((hasRead = is.read(buff))>0){

                raf.write(buff,0,hasRead);
            }



            /*
            //总共多大
            long contentLon = end -start;
            //最多取几次
            long times = contentLon/BUFF_LEN +4;

            System.out.println("times=="+times);

            for (int i= 0;i<times;i++){

                //读入buff
                hasRead = is.read(buff);

                if (hasRead<0){

                    break;

                }
                System.out.println("T-name="+getName());

                //写入文件
                raf.write(buff,0,hasRead);
            }*/


        }catch (Exception e){
            e.printStackTrace();
        }finally {

            System.out.println("finally="+getName());

            try {
                if (is != null){
                    System.out.println(getName()+"-isClose");
                    is.close();
                }
                if (raf != null){
                    raf.close();
                    System.out.println(getName()+"-rafClose");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
