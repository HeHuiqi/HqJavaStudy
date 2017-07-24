package com.hhq.study.HqAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by macpro on 2017/7/24.
 */
//只对方法起作用
@Target(ElementType.METHOD)
//保留到运行时
@Retention(RetentionPolicy.RUNTIME)

public @interface HqRequestAnnotation {


    String url() default "";
    String method() default "get";
}
