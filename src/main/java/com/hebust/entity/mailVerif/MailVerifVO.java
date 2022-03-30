package com.hebust.entity.mailVerif;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailVerifVO {

    private int status;  //返回状态码
    private String message; //返回消息
    private String code;    // 返回随机的验证码
    private Object object; //返回的对象
}
