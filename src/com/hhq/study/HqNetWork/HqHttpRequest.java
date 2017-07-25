package com.hhq.study.HqNetWork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Created by hehuiqi on 7/25/17.
 */
public class HqHttpRequest {

    //get请求
    public  static  String sendGet(String url,String param){

        String result = "";
        BufferedReader in = null;
        try {


            String urlName = url+"?"+param;
            if (param ==null){
                urlName = url;
            }
            URL realUrl = new URL(urlName);
            //打开连接
            URLConnection con = realUrl.openConnection();
            //设置请求头
            con.setRequestProperty("accept","*");
            con.setRequestProperty("connection","keep-alive");

            //建立连接
            con.connect();
            Map<String,List<String>> map = con.getHeaderFields();
            for (String  key:
                 map.keySet()) {
                System.out.println(key+"---->"+map.get(key));

            }
            //获取响应数据
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null){
                result += "\n" +line;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (in != null){
                    in.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return result;
    }

    //post请求
    public  static String sendPost(String url,String param){
        String result = "";
        PrintWriter out = null;
        BufferedReader in = null;

       try {

           URL realUrl = new URL(url);

           //打开连接
           URLConnection con =  realUrl.openConnection();
           con.setRequestProperty("accept","*");
           con.setRequestProperty("connection","keep-alive");

           //发送post请求必须设置
           con.setDoOutput(true);
           con.setDoInput(true);

           //获取输出应流
           out = new PrintWriter(con.getOutputStream());
           //发送请求参数
           out.print(param);
           //flush输出流的缓冲
           out.flush();

           //获取响应
           in = new BufferedReader(new InputStreamReader(con.getInputStream()));
           String line = null;
           while ((line = in.readLine()) != null){
               result += "\n"+line;
           }
           return result;



       }catch (Exception e){
           e.printStackTrace();
       }finally {
           try {
               if (out !=null){
                   out.close();
               }
               if (in != null){
                   in.close();
               }
           }catch (Exception e){
               e.printStackTrace();
           }
       }

        //打开连接


        return result;

    }
}
