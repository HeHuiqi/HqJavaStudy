package com.hhq.study.HqAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by macpro on 2017/7/24.
 */

//作用与变量
@Target(ElementType.FIELD)
//保留到运行时
@Retention(RetentionPolicy.RUNTIME)

public @interface HqAnnoField {
    String name() default "";
    int type() default 1;
}
