package com.hebust.entity.mailVerify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailVerifyVO {

    private int status;  //返回状态码
    private String message; //返回消息
    private Object object; //返回的对象
}
