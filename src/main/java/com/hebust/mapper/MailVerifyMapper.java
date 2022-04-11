package com.hebust.mapper;

import com.hebust.entity.mailVerify.MailVerify;

public interface MailVerifyMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByEmail(String email);

    int insert(MailVerify record);

    int insertSelective(MailVerify record);

    MailVerify selectByPrimaryKey(Integer id);

    String selectVerifyCodeByEmail(String email);

    int updateByPrimaryKeySelective(MailVerify record);

    int updateByPrimaryKey(MailVerify record);

    int updateVerifyCodeByEmail(MailVerify record);

    int isEmailExist(String email);




}