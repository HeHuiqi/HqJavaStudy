package com.hhq.study.HqReflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class HqClassLoader extends ClassLoader {

    //读取源码文件
    private  byte[] getBytes(String filename) throws  Exception{

        File file = new File(filename);

        long fileLength = file.length();

        byte[] readByte = new byte[(int) fileLength];
        FileInputStream fin = new FileInputStream(file);
        int hasRead = fin.read(readByte);
        if (hasRead != fileLength){
            System.out.println("无法读取全部文件。。。");
        }

        fin.close();

        return readByte;

    }

    //定义编译指定文件的方法

    private  boolean compile(String javaFile) throws IOException{

        System.out.println("正在编译"+javaFile+"");
        //调用系统命令javac
        Process process = Runtime.getRuntime().exec("javac"+javaFile);

        try {
            //让其他线程等待这个线程完成编译
            process.waitFor();
        }catch (Exception e){
            e.printStackTrace();

        }
        int result = process.exitValue();

        return result==0;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        return super.findClass(name);

        Class clazz = null;

        //将路径的.换成/
        String fileSubPath =  name.replace(".","/");
        String javaFileName = fileSubPath + ".java";
        String classFileName = fileSubPath+".class";

        File javafile = new File(javaFileName);
        File classFile = new File(classFileName);
        //指定java源文件存在，class文件不存在或者java源文件的修改时间
        //比clas文件的修改时间晚,则重新编译
        if (javafile.exists()
                && (!classFile.exists() || javafile.lastModified()>classFile.lastModified())
                ){
            try {

                if (!compile(javaFileName) || !classFile.exists()){
                    throw  new ClassNotFoundException("ClassNotFoundException"+javaFileName);
                }


            }catch (Exception e){
                e.printStackTrace();
            }

        }

        //如果class文件存在，系统负责把该文件转为Class对象
        if (classFile.exists()){

            try {

                //读取class文件写入byte[]数组
                byte[] classByte = getBytes(classFileName);
                //调用ClassLoadr的defineClass()方法将数据转为Class对象
                clazz = defineClass(name,classByte,0,classByte.length);

            }catch (Exception e){

                e.printStackTrace();
            }

        }else {

            System.out.println(name+" class文件不存在");
        }
        if (clazz == null){

            throw  new ClassNotFoundException(name);
        }

        return clazz;
    }


    public  static void  main(String[] args) throws  Exception{

        if (args.length==0){

            System.out.println("取少运行时的目标类，请按如下格式来运行：");
            System.out.println("java HqClassLoader ClassName");
        }



        //第一个参数时运行时需要的类
        String progress = args[0];
        //剩下的参数作为目标类
        String[] proArgs = new String[args.length-1];
        System.arraycopy(args,1, proArgs,0,proArgs.length);

        HqClassLoader cc = new HqClassLoader();
        //加载运行需要的类
        Class<?> clazz = cc.loadClass(progress);
        Method mainMethod = clazz.getMethod("Main",(new  String[0].getClass()));

        Object argsArray[] = {proArgs};
        mainMethod.invoke(null,argsArray);


    }

}
