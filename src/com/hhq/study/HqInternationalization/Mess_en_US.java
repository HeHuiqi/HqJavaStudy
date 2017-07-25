package com.hhq.study.HqInternationalization;

import java.util.ListResourceBundle;

/**
 * Created by hehuiqi on 7/16/17.
 */
// 使用累文件替代资源文件，程序优先加载类文件
public class Mess_en_US extends ListResourceBundle {

    private  final Object[][] myData = {
            {"hello","欢迎你！"},
            {"login","登录"}
    };
    @Override
    protected Object[][] getContents() {

        return myData;
    }
}
