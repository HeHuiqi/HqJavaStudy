package com.hhq.study.HqInternationalization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by hehuiqi on 7/24/17.
 */
public class HqInternationnalUse {

    public  void  myinternational(){

        //        Locale[]  locales = Locale.getAvailableLocales();
//        for (int i = 0;i<locales.length;i++) {
//            Locale local = locales[i];
//            System.out.println(local.getDisplayCountry()+"="+local.getCountry()+" "+local.getDisplayLanguage()+"="+local.getLanguage());
//
//        }

        Locale myLocale = Locale.getDefault();
        //优先加载资源类Mess_en_US,找不到再加载资源文件
        ResourceBundle bundle = ResourceBundle.getBundle("com.hhq.study.HqInternationalization.Mess",myLocale);
        System.out.println(bundle.getString("hello"));
        System.out.println(bundle.getString("login"));

        String msg = "你好{0}!今天是{1}";
        System.out.println(MessageFormat.format(msg,"小何","7月16日"));

    }
}
