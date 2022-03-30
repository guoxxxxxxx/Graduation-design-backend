package com.hebust;

import com.hebust.utils.mail.RandomCodeUtils;
import com.hebust.utils.mail.SendRandomCode;
import org.junit.Test;

public class EmailTest {

    @Test
    public void email_test(){
        String email = "guo_x0315@163.com";
        SendRandomCode.sendMessage(RandomCodeUtils.getRandomCode(), email);
        System.out.println("发送完毕");
    }
}
