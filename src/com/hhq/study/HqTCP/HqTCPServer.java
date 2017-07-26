package com.hhq.study.HqTCP;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HqTCPServer {

    public ServerSocket serverSocket = null;

    public void createServerSocket() throws Exception{

        serverSocket = new ServerSocket(30000);

        while (true){

            Socket socket = serverSocket.accept();

            //处理流
            PrintStream ps = new PrintStream(socket.getOutputStream());

            ps.println("你好，新年快乐");
            ps.close();
            socket.close();
        }

    }
}
