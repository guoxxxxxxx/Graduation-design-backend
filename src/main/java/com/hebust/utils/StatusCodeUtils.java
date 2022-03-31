package com.hebust.utils;

public class StatusCodeUtils {

    /**
     * 成功 200
     */
    public static final StatusCode SUCCESS;


    /**
     * 失败 400
     */
    public static final StatusCode FAIL;


    /**
     * 邮箱不存在 401
     */
    public static final StatusCode EMAIL_NOT_EXIST;


    /**
     * 邮箱存在 201
     */
    public static final StatusCode EMAIL_EXIST;


    static {
        SUCCESS = new StatusCode("success", 0);
        FAIL = new StatusCode("fail", 1);
        EMAIL_NOT_EXIST = new StatusCode("fail", 401);
        EMAIL_EXIST = new StatusCode("success", 201);
    }
}
