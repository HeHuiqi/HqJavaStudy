package com.hhq.study.HqNetWork;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by macpro on 2017/7/25.
 */
public class HqMutilDownManager {

    final  int DOWN_THREAD_NUM = 4;
    final  String OUT_FILENAME = "mutil_download.jpg";
    InputStream[] isArr = new InputStream[DOWN_THREAD_NUM];

    RandomAccessFile[] outArr = new RandomAccessFile[DOWN_THREAD_NUM];

    public void  startDownLoad(){

        try {

            String urlstr = "http://pic.58pic.com/58pic/13/75/00/66R58PICKvP_1024.jpg";
            URL url = new URL(urlstr);
            isArr[0] = url.openStream();
            long fileLength = getFileLength(url);
            outArr[0] = new RandomAccessFile(OUT_FILENAME,"rw");

            System.out.println("fileSize = "+fileLength);
            //创建一个于下载文件相同大小的文件
            for (int i = 0;i<fileLength;i++){
                outArr[0].write(0);
            }
            long numPerThread = fileLength/DOWN_THREAD_NUM;
            //剩余
            long left = fileLength%DOWN_THREAD_NUM;

            for (int i = 0;i<DOWN_THREAD_NUM;i++){


                if (i !=0){

                    isArr[i] = url.openStream();
                    outArr[i] = new RandomAccessFile(OUT_FILENAME,"rw");
                }
                //开始启动多线程下载
                if (i == DOWN_THREAD_NUM -1){

                    System.out.println("i==="+i);
                    //最后一个线程下载剩余的全部文件
                    new HqDownThread(i*numPerThread, (i+1)*numPerThread+left,isArr[i],outArr[i]).start();

                }else {
                    System.out.println("i="+i);

                    new HqDownThread(i*numPerThread,(i+1)*numPerThread,isArr[i],outArr[i]).start();

                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public  static  long getFileLength(URL url) throws  Exception{
        long length = 0;
        //打开连接
        URLConnection con = url.openConnection();
        length = con.getContentLength();
        return length;

    }

    public  void simpleDownload() throws  Exception{

        String urlstr = "http://pic.58pic.com/58pic/13/75/00/66R58PICKvP_1024.jpg";
        URL url = new URL(urlstr);

        InputStream in = url.openStream();

        int fileLength = (int)getFileLength(url);

        byte[] buff = new byte[fileLength];

        File file = new File("download.jpeg");
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);

        int hasRead = 0;
        while ((hasRead=in.read(buff))>0){

            fos.write(buff,0,hasRead);
        }

        fos.close();
        in.close();

    }
}
