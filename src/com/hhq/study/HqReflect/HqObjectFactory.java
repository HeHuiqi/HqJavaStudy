package com.hhq.study.HqReflect;

import javax.swing.*;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.*;

public class HqObjectFactory {

    //定义一个对象池 对象名，对象
   private Map<String,Object> objectPool = new HashMap<String, Object>();

   private Properties config = new Properties();

   //从指定属性文件中初始化Properties对象
    public  void  init(String fileName){

        FileInputStream fis = null;

        try {
            String path = HqObjectFactory.class.getClassLoader().getResource(fileName).getPath();
            fis = new FileInputStream(path);
            config.load(fis);

        }catch (Exception e){

            System.out.println("读取"+fileName+"异常");
        }finally {
            try {
                if (fis != null){
                    fis.close();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //创建一个对象的方法
    private  Object createObject(String clazzName) throws  Exception{

        //根据字符串来获取对应的Class对象
        Class<?> clazz = Class.forName(clazzName);
        //使用clazz对应的默认构造器来创建默认实例
        return  clazz.newInstance();
    }

    //指定一个配置文件来初始化对象池
    public void  initPool()throws Exception{

        for (String name : config.stringPropertyNames()) {
            //每取出一个键值对， 如果key中不包含百分号（%）
            //就会根据value创建一个对象
            if (!name.contains("%")){
                objectPool.put(name,createObject(config.getProperty(name)));
            }
        }
        System.out.println("config="+config);
    }


    //根据指定配置文件来初始化对象池，并调用相应的方法设置属性值
    public  void  initProperty() throws  Exception{

        for (String name:config.stringPropertyNames()){

            //每取出一个键值对， 如果key中包含百分号（%）,可认为是用于为此对象设置属性值
            //就会根据value创建一个对象
            //会调用相应的setter方法来设置属性值
            if (name.contains("%")){
                //将key以%分割,取出对象名和属性名
                String[] objAndPro = name.split("%");

                //取出目标对象
                Object target =  getObject(objAndPro[0]);
                //该属性对应的方法名为set+属性名首字母大写+剩余部分如 setTitle
                String filedName = objAndPro[1];
                String methodName = "set"+
                        filedName.substring(0,1).toUpperCase()+
                        filedName.substring(1);
//                System.out.println("methodName = "+methodName);
                //获取target的Class对象
                Class<?> targetClazz = target.getClass();
                //获取方法 参数为方法名，参数类型的class对象...
//                System.out.println(targetClazz);
                String value = config.getProperty(name);
//                System.out.println("value = "+value);
                Method mtd = targetClazz.getMethod(methodName,String.class);
                //通过Method的invoke方法来调用方法
                mtd.invoke(target,value);


            }

        }
    }

    public Object getObject(String name){

        //从对象池中取出对象
        return objectPool.get(name);
    }


    public  static void  main(String[] args) throws  Exception{

        com.hhq.study.HqReflect.
        HqObjectFactory hqObjectFactory = new HqObjectFactory();
        hqObjectFactory.init("com/hhq/study/HqReflect/HqObject.properties");
        hqObjectFactory.initPool();
        hqObjectFactory.initProperty();
        System.out.print("hqJLab=="+hqObjectFactory.getObject("hqJLab"));

    }


}
