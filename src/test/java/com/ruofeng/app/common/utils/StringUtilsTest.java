package com.ruofeng.app.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.CaseFormat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtilsTest {
    /**
     * LOWER_CAMEL : java变量命名方式,首字母小写的驼峰,eg:caseFormat<br>
     * UPPER_CAMEL : <br>
     * LOWER_UNDERSCORE: <br>
     * UPPER_UNDERSCORE : 常量命名法,eg:CASE_FORMAT <br> 
     * 
     * @param word
     * @return
     */
    @Test
    public void nameTypeConversion(){
        String source="tb_sample_clothes_price";
        CaseFormat fromFormat = CaseFormat.LOWER_UNDERSCORE;
        Map<String,Object>paramMap=new HashMap<String,Object>();
        paramMap.put("source", source);
        paramMap.put("UPPER_CAMEL",fromFormat.to(CaseFormat.UPPER_CAMEL, source));
        paramMap.put("LOWER_CAMEL",fromFormat.to(CaseFormat.LOWER_CAMEL, source));
        String tpl="\n原始字符串是:${source},\n转换成LOWER_CAMEL方式:${LOWER_CAMEL}\n转换成UPPER_CAMEL方式:${UPPER_CAMEL}";
        System.out.println(StringUtils.templateIt(tpl,paramMap));
    }
    @Test
    public void testPackageToUrl(){
        String url="com.tencent.android.study";
        System.out.println(StringUtils.packageToUrl(url,3));
        System.out.println(StringUtils.packageToUrl(url,-3));
    }

    @Test
    public void testSubSpliString(){
        String url="com/abc/app/kjTest/user";
        System.out.println(StringUtils.subSpliString(url,"/","/",-3));
    }
}
