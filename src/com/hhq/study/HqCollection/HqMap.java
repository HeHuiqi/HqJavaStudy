package com.hhq.study.HqCollection;

import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
public class HqMap{
    public static void main(String[] args){
        System.out.println("Map");
        HashMap hm = new HashMap();
        hm.put("name","小明");
        hm.put("degree",2);
        System.out.println(hm);
        System.out.println("name ="+hm.get("name"));
        //修改如果key重复则覆盖
        hm.put("name","小花");
        System.out.println("name ="+hm.get("name"));

        //创建线程安全的map
        Map personInfo = Collections.synchronizedMap(new HashMap());
        personInfo.put("address","澳大利亚");
        System.out.println(personInfo);
    }
}