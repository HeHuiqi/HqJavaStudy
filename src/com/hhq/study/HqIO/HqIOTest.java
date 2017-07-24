package com.hhq.study.HqIO;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by macpro on 2017/7/24.
 */
public class HqIOTest {

    @Test
    public void myFileTest() throws Exception {

        new HqFile().myfileFilter();
    }
    @Test

    public  void  readFileTest() throws  Exception{

        new HqFile().readFile();

    }

    @Test
    public  void  writeFileTest() throws  Exception{

        new HqFile().writeFile();
    }

    @Test
    public void serializableTest() throws Exception {

        new HqSerializableUse().savePerson();
        new HqSerializableUse().readPerson();

    }

    @Test
    public void myBufferTest() throws Exception {

        new HqBufferUse().myBuffer();
    }


    @Test
    public  void  channelTest() throws  Exception{

        new HqChannelUse().myChannel();
        new  HqChannelUse().userChannelReadFile();

    }


    @Test
    public  void  charsetTest(){


    }

}