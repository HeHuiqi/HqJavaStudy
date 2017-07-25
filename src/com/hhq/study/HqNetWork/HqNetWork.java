package com.hhq.study.HqNetWork;


import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static java.net.InetAddress.*;

/**
 * Created by macpro on 2017/7/25.
 */
public class HqNetWork {

    public  void  hqInetAddressUse() throws  Exception{

        InetAddress ip =  InetAddress.getByName("ww.baidu.com");
        System.out.println("ipAddress="+ip.getHostAddress());

        //获取全限定域名
        System.out.println("host1 = "+ip.getCanonicalHostName());

        //是否可达
        System.out.println("reachable = "+ip.isReachable(5000));

        InetAddress local = InetAddress.getByAddress(new byte[]{127,1,1,0});
        System.out.println("local="+local);

        //获取全限定域名
        System.out.println("host = "+local.getCanonicalHostName());
    }

    public  void  hqURLCoder() throws  Exception{

        //%25E4%25BD%25A0%25E5%25A5%25BD 就代表 application/x-www-form-urlencoded MIME
        String str = URLDecoder.decode("%25E4%25BD%25A0%25E5%25A5%25BD","UTF-8");
        System.out.println("str = "+str);
        str = URLDecoder.decode(str,"utf-8");
        System.out.println("str = "+str);

        String encoderStr = URLEncoder.encode("你好","utf-8");
        System.out.println("encoder="+encoderStr);
    }
}
