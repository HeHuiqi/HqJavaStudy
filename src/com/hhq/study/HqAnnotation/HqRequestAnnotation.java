package com.hhq.study.HqAnnotation;

import java.lang.annotation.*;

/**
 * Created by macpro on 2017/7/24.
 */
//只对方法起作用
@Target(ElementType.METHOD)
//保留到运行时
@Retention(RetentionPolicy.RUNTIME)

//会被javadoc工具生成的api提取出相应的注解
@Documented


public @interface HqRequestAnnotation {


    String url() default "";
    String method() default "get";
}
