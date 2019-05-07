package com.ruofeng.app.framework.codegen.functions;

import com.google.common.base.CaseFormat;
import com.ruofeng.app.common.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class KotlinFun {
    public static Map<String, String> sqlTypeMap = new HashMap<String, String>();

    public KotlinFun() {
        sqlTypeMap.put("varchar", "String");
        sqlTypeMap.put("char", "String");
        sqlTypeMap.put("decimal", "BigDecimal");
        sqlTypeMap.put("date", "Date");
        sqlTypeMap.put("datetime", "Timestamp");
        sqlTypeMap.put("timestamp", "Timestamp");
        sqlTypeMap.put("integer", "Long");
        sqlTypeMap.put("int", "Long");
        sqlTypeMap.put("bigint", "BigInteger");
        sqlTypeMap.put("tinyint", "Integer");
        sqlTypeMap.put("float", "Float");
        sqlTypeMap.put("smallint", "Integer");
        sqlTypeMap.put("mediumint", "Integer");
    }

    public static String lowerFirst(String word) {
        String var10000 = StringUtils.lowerFirst(word);
        return var10000;
    }

    public static String upperFirst(String word) {
        return StringUtils.upperFirst(word);
    }

    public static String dbcolToClzAttr(String dbcol) {
        String var10000 = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, dbcol);
        return var10000;
    }

    /**
     * 下划线转化成驼峰（首字母大写）
     */
    public static String underToUpperCamel(String dbcol) {
        String var10000 = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, dbcol);
        return upperFirst(var10000);
    }

    /**
     * 下划线转化成驼峰（首字母不作处理）
     */
    public static String underToCamel(String dbcol) {
        return dbcolToClzAttr(dbcol);
    }

    public static Boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String && ((String) obj).trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static String packageToUrl(String word) {
        return StringUtils.packageToUrl(word,0);
    }
    public static String packageToUrl(String word,Integer depth) {
        return StringUtils.packageToUrl(word,depth);
    }
    public static String deleteLastChar(String word) {
        return word.substring(0, word.length() - 1);
    }

    public static Object turnSqlType(String sqlType) {

        return sqlTypeMap.get(sqlType);
    }

}
