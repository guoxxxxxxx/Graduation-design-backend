package com.hebust;

import com.hebust.entity.mailVerify.MailVerify;
import com.hebust.service.MailService;
import com.hebust.utils.mail.RandomCodeUtils;
import com.hebust.utils.mail.SendRandomCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailTest {

    private String testEmail = "guo_x0315@163.com";
    private String testVerifyCode = "asAS2d";

    @Autowired
    private MailService mailService;

    @Test
    public void email_test(){
        String email = "guo_x0315@163.com";
        SendRandomCode.sendMessage(RandomCodeUtils.getRandomCode(), email);
        System.out.println("发送完毕");
    }

    /**
     * 检查数据插入是否正常
     */
    @Test
    public void t1(){
        MailVerify mailVerify = new MailVerify();
        mailVerify.setEmail(testEmail);
        mailVerify.setVerifyCode(testVerifyCode);
        int i = mailService.insertSelective(mailVerify);
        System.out.println("插入了 ： " + i);
    }



}
