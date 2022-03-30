package com.hebust.utils;

public class StatusCodeUtils {
    public static final StatusCode SUCCESS;
    public static final StatusCode FAIL;
    static {
        SUCCESS = new StatusCode("success", 0);
        FAIL = new StatusCode("fail", 1);
    }
}
