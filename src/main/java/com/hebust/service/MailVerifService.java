package com.hebust.service;

public interface MailVerifService {

    /**
     * 通过邮箱添加验证码
     * @return 添加的验证码
     */
    String insertRecord(String email);

    /**
     * 通过邮箱查询验证码
     * @return 验证码
     */
    String selectRandomCodeByEmail(String email);

    /**
     * 通过邮箱更新验证码
     * @return 最新的验证码
     */
    String updateRandomCodeByEmail(String email);

    /**
     * 通过邮箱删除记录
     * @return 受影响的行数
     */
    int deleteRecordByEmail(String email);
}
