package com.hebust.controller;

import com.hebust.entity.mailVerif.MailVerif;
import com.hebust.entity.mailVerif.MailVerifVO;
import com.hebust.mapper.UserMapper;
import com.hebust.service.UserService;
import com.hebust.service.imple.UserServiceImpl;
import com.hebust.utils.StatusCodeUtils;
import com.hebust.utils.mail.RandomCodeUtils;
import com.hebust.utils.mail.SendRandomCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailVerifController {

    @Autowired
    private UserService mapper;

    /**
     * 获取验证码(当邮箱存在时调用该方法)
     */
    @RequestMapping("/getCode")
    public MailVerifVO getCode(String email){
        if (email == null || email.equals("")){
            // 若邮箱为空发送400错误
            return new MailVerifVO(400, "fail", "null",null);
        } else {
            if (mapper.selectByEmail(email) == 1){
                // 若邮箱存在于数据库中，发送验证码
                String randomCode = RandomCodeUtils.getRandomCode();
//                SendRandomCode.sendMessage(randomCode, email);
                return new MailVerifVO(200, "success", randomCode, null);
            } else {
                // 若邮箱不存在数据库中 发送401状态码
                return new MailVerifVO(401, "fail", null, null);
            }
        }
    }

    /**
     * 获取邮件验证码，注册时调用该方法
     */
    @RequestMapping("/getRegisterCode")
    public MailVerifVO getRegisterCode(String email){
        if (email == null || email.equals("")){
            return new MailVerifVO(400, "fail", "null",null);
        } else {
            // 随机生成6位验证码
            String randomCode = RandomCodeUtils.getRandomCode();
            // 向用户发送邮件
//            SendRandomCode.sendMessage(randomCode, email);
            return new MailVerifVO(200, "success", randomCode, null);
        }
    }
}
