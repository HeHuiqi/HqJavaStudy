package com.hhq.study.HqTCP;

import org.junit.Test;

import static org.junit.Assert.*;

public class HqTCPSocketTest {

    @Test
    public void testServerSocket() throws  Exception{
        new HqTCPServer().createServerSocket();
    }

    @Test
   public void  testSocketClient() throws  Exception{

        new  HqTCPSocketClient().createClientSocket();
    }

}