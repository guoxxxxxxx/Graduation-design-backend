package com.hebust.service.imple;

import com.hebust.entity.mailVerify.MailVerify;
import com.hebust.mapper.MailVerifyMapper;
import com.hebust.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailVerifyMapper mailVerifyMapper;

    @Override
    public int isEmailExist(String email) {
        return mailVerifyMapper.isEmailExist(email);
    }

    @Override
    @Transactional
    public int insertSelective(MailVerify record) {
        return mailVerifyMapper.insertSelective(record);
    }

    @Override
    @Transactional
    public int updateVerifyCodeByEmail(MailVerify record) {
        return mailVerifyMapper.updateVerifyCodeByEmail(record);
    }

    @Override
    public String selectVerifyCodeByEmail(String email) {
        return mailVerifyMapper.selectVerifyCodeByEmail(email);
    }

    @Override
    public int deleteByEmail(String email) {
        return mailVerifyMapper.deleteByEmail(email);
    }
}
