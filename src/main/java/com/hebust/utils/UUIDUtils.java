package com.hebust.utils;

import java.util.UUID;

/**
 * 获取唯一的UUID
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
