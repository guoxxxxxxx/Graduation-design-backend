package com.hebust.controller;

import com.hebust.entity.mailVerif.MailVerif;
import com.hebust.entity.mailVerif.MailVerifVO;
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

    /**
     * 获取验证码
     */
    @RequestMapping("/getCode")
    public MailVerifVO getCode(String email){
        if (email == null || email.equals("")){
            return new MailVerifVO(400, "fail", "null",null);
        } else {
            String randomCode = RandomCodeUtils.getRandomCode();
//            SendRandomCode.sendMessage(randomCode, email);
            return new MailVerifVO(200, "success", randomCode, null);
        }
    }
}
