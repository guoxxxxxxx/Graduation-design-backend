package com.hebust.utils;

/**
 * 精简详细信息工具类
 */
public class SimplifyDetailsUtils {

    /**
     * 精简字符串，将字符串压缩到35个字符串长度 + ...
     */
    public static String simplifyDetails(String s){
        if (s != null && s.length() > 35)
            return s.substring(0, 35) + "...";
        else
            return s;
    }
}
