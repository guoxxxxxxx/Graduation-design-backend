package com.hebust.controller;

import com.hebust.entity.mailVerify.MailVerify;
import com.hebust.entity.mailVerify.MailVerifyVO;
import com.hebust.service.MailService;
import com.hebust.service.UserService;
import com.hebust.utils.mail.CheckEmail;
import com.hebust.utils.mail.RandomCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/mail")
public class MailVerifyController {

    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;

    /**
     * 获取验证码(当邮箱存在时调用该方法)
     */
    @RequestMapping("/getCode")
    public MailVerifyVO getCode(String email){
        if (CheckEmail.isEmail(email)){
            // 若邮箱为空发送410错误
            return new MailVerifyVO(400, "fail",null);
        } else {
            if (userService.selectByEmail(email) == 1){
                // 若邮箱存在于数据库中，发送验证码
                String randomCode = RandomCodeUtils.getRandomCode();
                // 创建验证码对象
                MailVerify mailVerify = new MailVerify();
                mailVerify.setEmail(email);
                mailVerify.setVerifyCode(randomCode);
                // 如果邮箱存在于数据库中则执行更新操作
                if (mailService.isEmailExist(email) == 1){
                    mailService.updateVerifyCodeByEmail(mailVerify);
                } else {
                    // 否则执行插入操作
                    mailService.insertSelective(mailVerify);
                }
//                SendRandomCode.sendMessage(randomCode, email);
                return new MailVerifyVO(200, "success", null);
            } else {
                // 若邮箱不存在数据库中 发送401状态码
                return new MailVerifyVO(401, "fail", null);
            }
        }
    }

    /**
     * 获取邮件验证码，注册时调用该方法
     */
    @RequestMapping("/getRegisterCode")
    public MailVerifyVO getRegisterCode(String email){
        if (CheckEmail.isEmail(email)){
            return new MailVerifyVO(400, "fail",null);
        } else {
            // 随机生成6位验证码
            String randomCode = RandomCodeUtils.getRandomCode();
            MailVerify mailVerify = new MailVerify();
            mailVerify.setEmail(email);
            mailVerify.setVerifyCode(randomCode);
            // 如果邮箱存在于数据库中则执行更新操作
            if (mailService.isEmailExist(email) == 1){
                mailService.updateVerifyCodeByEmail(mailVerify);
            } else {
                // 否则执行插入操作
                mailService.insertSelective(mailVerify);
            }
            // 向用户发送邮件
//            SendRandomCode.sendMessage(randomCode, email);
            return new MailVerifyVO(200, "success", null);
        }
    }

    /**
     * 校验验证码
     */
    @RequestMapping("/verify")
    public MailVerifyVO verify(@RequestBody MailVerify mailVerify){
        // 查询该邮箱的验证码
        String verifyCode = mailService.selectVerifyCodeByEmail(mailVerify.getEmail());
        // 判断是否出错
        if (verifyCode == null){
            mailService.deleteByEmail(mailVerify.getEmail());
            return new MailVerifyVO(400, "fail", null);
        }
        // 将数据库中的验证码与传递过来的验证码做对比
        else if (verifyCode.equalsIgnoreCase(mailVerify.getVerifyCode())){
            // 比对成功后从数据库中删除验证码
            mailService.deleteByEmail(mailVerify.getEmail());
            return new MailVerifyVO(200, "success", null);
        } else {
            return new MailVerifyVO(400, "fail", null);
        }
    }
}
