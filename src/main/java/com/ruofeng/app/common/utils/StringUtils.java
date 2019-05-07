package com.ruofeng.app.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Ascii;
import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

public class StringUtils {
    private final static StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
    private static Configuration cfg = null;

    static {
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final static GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);

    public StringUtils() throws IOException {
    }

    public static final String lowerFirst(String $receiver) {
        CharSequence var1 = (CharSequence) $receiver;
        if (var1.length() == 0) {
            return $receiver;
        } else {
            StringBuilder var3 = new StringBuilder($receiver.length());
            char[] var10000 = $receiver.toCharArray();
            char[] var4 = var10000;
            StringBuilder var6 = var3.append(Ascii.toLowerCase(var4[0]));
            byte var2 = 1;
            var3 = var6;
            String var7 = $receiver.substring(var2);
            String var5 = var7;
            var7 = var3.append(var5).toString();
            return var7;
        }
    }

    /**
     * LOWER_CAMEL : java变量命名方式,首字母小写的驼峰,eg:caseFormat<br>
     * UPPER_CAMEL : java类命名方式,eg:CaseFormat<br>
     * LOWER_UNDERSCORE:全小写的下划线，eg:case_format <br>
     * UPPER_UNDERSCORE : 常量命名法,eg:CASE_FORMAT <br>
     *
     * @param word
     * @return
     */
    public static final String upperFirst(String word) {
        CaseFormat fromFormat = CaseFormat.LOWER_CAMEL;
        CaseFormat toFormat = CaseFormat.UPPER_CAMEL;
        return fromFormat.to(toFormat, word);
    }

    /**
     * 将src进行模板转义
     *
     * @param src
     * @param paramMap
     * @return
     */
    public static String templateIt(String src, Map<String, Object> paramMap) {
        Template t = gt.getTemplate(src);
        t.binding(paramMap);
        String str = t.render();
        return str;
    }
    public static String packageToUrl(String urlStr) {
        return subSpliString(urlStr, ".", "/", 0);
    }
    public static String packageToUrl(String urlStr, Integer depth) {
        return subSpliString(urlStr, ".", "/", depth);
    }

    public static String subSpliString(String source, String sourceDivider, String targetDivider, Integer depth) {
        StringBuilder result = new StringBuilder();
        List<String> leftList = Splitter.on(sourceDivider).trimResults().splitToList(source);
        if (depth > 0) {
            Joiner.on(targetDivider).appendTo(result, leftList.subList(0, depth));
        } else if (depth < 0) {
            Joiner.on(targetDivider).appendTo(result, leftList.subList(leftList.size() + depth, leftList.size()));
        } else if (depth == 0) {
            Joiner.on(targetDivider).appendTo(result, leftList);
        }
        return result.toString();
    }
}
