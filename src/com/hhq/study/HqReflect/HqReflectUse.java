package com.hhq.study.HqReflect;

import java.net.URL;
import java.util.Enumeration;

public class HqReflectUse {

    public  void  getClassLoaderURLs(){

        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();

        for (URL url :
                urls) {
            System.out.println("Bootstrap-url=="+url);

        }
    }

    public  void   getSystemClassLoader()throws  Exception {

        ClassLoader sysc = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器："+sysc);

        Enumeration<URL> em1 = sysc.getResources("");
        while (em1.hasMoreElements()){

            System.out.println("sys-url="+em1.nextElement());
        }

        ClassLoader extensionLoader = sysc.getParent();
        System.out.println("扩展类加载器："+extensionLoader);
        System.out.println("扩展类加载器路径："+System.getProperty("java.ext.dirs"));
        System.out.println("扩展类加载器的父类："+extensionLoader.getParent());
    }

}
