package com.hhq.study.HqNetWork;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by macpro on 2017/7/25.
 */
public class HqNetWorkTest {
    @Test
    public void testHqInetAddress() throws Exception {

        HqNetWork hqNetWork = new HqNetWork();
//        hqNetWork.hqInetAddressUse();
        hqNetWork.hqURLCoder();
    }

    @Test
    public  void  testMutilThreadDownLoad() throws  Exception{

        HqMutilDownManager hqMutilDownManager = new HqMutilDownManager();
        hqMutilDownManager.startDownLoad();
//        hqMutilDownManager.simpleDownload();
    }

    @Test

    public  void  testHttpRequest(){

       String string = HqHttpRequest.sendGet("https://www.baidu.com",null);
       System.out.println("res="+string);

    }

}