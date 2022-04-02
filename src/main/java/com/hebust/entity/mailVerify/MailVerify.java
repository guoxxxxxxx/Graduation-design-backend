package com.hebust.entity.mailVerify;

import java.io.Serializable;
import lombok.Data;

/**
 * mail_verify
 * @author 
 */
@Data
public class MailVerify implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 随机生成的验证码
     */
    private String verifyCode;

    private static final long serialVersionUID = 1L;
}