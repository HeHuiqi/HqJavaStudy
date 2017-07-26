package com.hhq.study.HqReflect;

import org.junit.Test;

import static org.junit.Assert.*;

public class HqReflectUseTest {

    @Test

    public  void  testClassLoaderURLs() throws  Exception{

        HqReflectUse hqReflectUse = new HqReflectUse();

        hqReflectUse.getClassLoaderURLs();
        hqReflectUse.getSystemClassLoader();
    }

}