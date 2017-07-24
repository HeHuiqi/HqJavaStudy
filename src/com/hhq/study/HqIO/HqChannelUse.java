package com.hhq.study.HqIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by macpro on 2017/7/24.
 */
public class HqChannelUse {

    public  void  myChannel(){

        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            File file = new File("channel.txt");
            //FileInputStream获取的channel只能读
            inChannel = new FileInputStream(file).getChannel();

            //映射为byteBuffer,只读模式 FileChannel.MapMode.READ_ONLY
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY,0,file.length());


            //创建输出通道，输出到 channelTest.txt文件
            //FileOutputStream获取的channel只能写
            outChannel = new FileOutputStream("channelTest.txt").getChannel();

            //追加Test后写入所有数据，这里在只读模式是将会抛出异常
//            buffer.put("Test".getBytes());
//            buffer.flip();

            outChannel.write(buffer);
            buffer.clear();


            //创建解码器
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset.newDecoder();

            //解码为CharBuffer
            CharBuffer charBuffer = decoder.decode(buffer);
            //输出
            System.out.println(charBuffer);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (inChannel != null || outChannel != null){
                try {
                    inChannel.close();
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public  void  userChannelReadFile(){

        FileChannel fc = null;

        try {

            FileInputStream fis = new FileInputStream("channel.txt");
            fc = fis.getChannel();
            //buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //读取数据
            while (fc.read(byteBuffer) != -1){

                byteBuffer.flip();

                //解码器
                Charset charset = Charset.forName("UTF-8");
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer charBuffer = decoder.decode(byteBuffer);
                System.out.println("ccc = "+charBuffer);
                byteBuffer.clear();
            }
        }catch (Exception e){

            e.printStackTrace();
        }finally {
            if (fc != null){
                try {
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
