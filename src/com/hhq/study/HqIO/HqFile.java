package com.hhq.study.HqIO;

import java.io.*;

/**
 * Created by macpro on 2017/7/24.
 */
public class HqFile {

    public  void  createFile() throws  Exception{

        File myfile = new File(".","heihei.txt");
        //创建文件
        myfile.createNewFile();

        //不指定文件名是在目录
        File file = new File(".");


        //获取文件名
        System.out.println("filename="+file.getName());
        //获取文件绝对路径
        System.out.println("absolutePath"+file.getAbsolutePath());
        //获取上一级路径
        System.out.println("parent="+file.getAbsoluteFile().getParent());

//        //文件名至少三个字符
//        File tempFile = File.createTempFile("haha",".txt",file);
//        tempFile.deleteOnExit();
//
//        File newFile = new File(System.currentTimeMillis()+"");
//        System.out.println("是否存在"+newFile.exists());



    }
    public  void  myfileFilter(){

        File file = new File(".");
        String[] filelist = file.list(new MyFileNameFilter());
        for (String name :
                filelist) {

            System.out.println( "name = "+name);
        }

    }

    public  void  readFile() {


        //创建字节输入流读取文件
        FileInputStream fis = null;
        InputStreamReader ireader = null;
        BufferedReader bfReader = null;
        try {

            fis = new FileInputStream("haha.txt");

            /*
            //创建一个临时存储
            byte[] buff = new  byte[1024];
            int haRead = 0;
            while ((haRead = fis.read(buff)) >0){

                //取出数据
                System.out.println(""+new String(buff,0,haRead));
            }

            */

            //如果处理的是文本建议将字节流包装为字符流在转换为处理流
            String buffstr = null;

            ireader = new InputStreamReader(fis);
            bfReader = new BufferedReader(ireader);
            while ((buffstr = bfReader.readLine()) != null){
                //取出数据
                System.out.println(buffstr);
            }

        }catch (Exception e){

            e.getLocalizedMessage();


        }finally {

            if (fis != null){
                try {
                    //关闭流
                    bfReader.close();
                    ireader.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void  writeFile() {


        File myfile = new File(".","huiqi.txt");
        FileOutputStream fos = null;
        PrintStream ps = null;
        try {
            //创建一个文件
            myfile.createNewFile();
            //根据文件创建一个输出流
            fos = new FileOutputStream(myfile);
            //将字符串写到文件中

            //节点流
//            fos.write("用输出流写入的数据".getBytes());

            //使用处理来写写入数据，包装节点流
            ps = new PrintStream(fos);
            ps.println("使用处理来直接写入数据");
            ps.println(new HqFile());


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

class  MyFileNameFilter implements FilenameFilter{

    @Override
    public  boolean accept(File dir,String name){

        return  name.endsWith(".java")|| new File(name).isDirectory();
    }

}
