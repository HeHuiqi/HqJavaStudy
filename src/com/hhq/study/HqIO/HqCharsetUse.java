package com.hhq.study.HqIO;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

/**
 * Created by macpro on 2017/7/24.
 */
public class HqCharsetUse {

    public  void  myCharset(){

        SortedMap<String,Charset> sortedMap = Charset.availableCharsets();
        for (String key :
                sortedMap.keySet()) {
            System.out.println(key + ":"+sortedMap.get(key));
        }

        Charset cn = Charset.forName("GBK");
        //编解码器
        CharsetEncoder charsetEncoder = cn.newEncoder();
        CharsetDecoder charsetDecoder = cn.newDecoder();

        CharBuffer cbf = CharBuffer.allocate(20);
        cbf.put('何');
        cbf.put('会');
        cbf.put('奇');
        cbf.flip();

        try {
            ByteBuffer byteBuffer = charsetEncoder.encode(cbf);
            for (int i =0;i<byteBuffer.capacity();i++){
                System.out.println(byteBuffer.get(i)+" ");
            }
            System.out.println(""+charsetDecoder.decode(byteBuffer));
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }

    }
}
