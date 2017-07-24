package com.hhq.study.HqIO;

import java.nio.CharBuffer;

/**
 * Created by macpro on 2017/7/24.
 */
public class HqBufferUse {


    public  void  myBuffer(){
        //创建一个字符buffer
        CharBuffer cbf = CharBuffer.allocate(8);
        System.out.println("capacity="+cbf.capacity());
        System.out.println("limit = "+cbf.limit());
        System.out.println("position="+cbf.position());
        //放入元素
        cbf.put('a');
        cbf.put('b');
        cbf.put('c');
        System.out.println("position="+cbf.position());
        //结束输入
        cbf.flip();
        System.out.println("limit = "+cbf.limit());
        System.out.println("position="+cbf.position());

        //取出第一个
        System.out.println("index=0="+cbf.get());
        System.out.println("position="+cbf.position());
        System.out.println("value="+cbf.get());

        //执行clear()方法后position回到0的位置,limit等于capacity
        cbf.clear();
        System.out.println("position="+cbf.position());
        System.out.println("limit = "+cbf.limit());
        //执行clear并不会清除值

        System.out.println("value="+cbf.get(2));
        //通过索引取值不会改变索引的位置
        System.out.println("position="+cbf.position());




    }
}

