package com.hebust.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * 状态码枚举类
 */
public enum StatusCodeUtils {
    SUCCESS(0, "success"), FAIL(1, "fail");

    // 定义属性
    private int code;
    private String message;

    StatusCodeUtils() {
    }

    StatusCodeUtils(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // 将toString方法, 将枚举对象转换为json
    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", code);
        jsonObject.put("msg", message);
        return jsonObject.toString();
    }
}
