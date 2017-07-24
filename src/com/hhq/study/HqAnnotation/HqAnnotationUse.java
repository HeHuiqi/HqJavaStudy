package com.hhq.study.HqAnnotation;

import java.lang.reflect.Field;

/**
 * Created by macpro on 2017/7/24.
 */

public class HqAnnotationUse {


    @HqAnnoField(name = "何会奇")
    private  String myName;

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    @HqRequestAnnotation(url = "https://www.baidu.com",method = "post")
    public  void  reqeustInfo() throws  Exception{

        HqRequestAnnotation hqRequestAnnotation = (HqRequestAnnotation) HqAnnotationUse.class.getMethod("reqeustInfo").getAnnotation(HqRequestAnnotation.class);

        String url = hqRequestAnnotation.url();
        System.out.println("url = "+hqRequestAnnotation.url());
        System.out.println("methond = "+hqRequestAnnotation.method());


        HqAnnoField hqAnnoField = (HqAnnoField)HqAnnotationUse.class.getDeclaredField("myName").getAnnotation(HqAnnoField.class);   ;
        System.out.println("name = "+hqAnnoField.name());

    }


}


