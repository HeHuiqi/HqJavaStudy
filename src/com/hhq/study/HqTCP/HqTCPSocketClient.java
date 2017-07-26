package com.hhq.study.HqTCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HqTCPSocketClient {

    public Socket socket = null;

    public  void  createClientSocket() throws  Exception{

        socket = new Socket("127.0.0.1",30000);
        //输入
        BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //IO
        String line = bf.readLine();

        System.out.println("来自服务器的消息："+line);
        bf.close();
        socket.close();
    }
}
