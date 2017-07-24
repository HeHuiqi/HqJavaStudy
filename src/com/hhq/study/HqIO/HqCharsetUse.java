package com.hhq.study.HqIO;

import java.nio.charset.Charset;
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

    }
}
