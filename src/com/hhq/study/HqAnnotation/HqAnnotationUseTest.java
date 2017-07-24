package com.hhq.study.HqAnnotation;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by macpro on 2017/7/24.
 */
// 选中某个类在编辑区，使用快捷键 shift+commond+T创建单元测试类
public class HqAnnotationUseTest {

    @Test
    public void reqeustInfo() throws Exception {
        new HqAnnotationUse().reqeustInfo();
    }

}