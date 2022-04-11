package com.hebust.service;

import com.hebust.entity.mailVerify.MailVerify;

public interface MailService {

    /**
     * 查询邮箱是否在存在于数据库中,
     * 存在则返回 1, 否则则返回 0
     */
    int isEmailExist(String email);

    /**
     * 向数据库中插入验证码
     */
    int insertSelective(MailVerify record);

    /**
     * 根据邮箱更新验证码
     */
    int updateVerifyCodeByEmail(MailVerify record);

    /**
     * 根据邮箱查询已经保存在数据库中的验证码
     */
    String selectVerifyCodeByEmail(String email);

    /**
     * 根据邮箱删除记录
     */
    int deleteByEmail(String email);
}
