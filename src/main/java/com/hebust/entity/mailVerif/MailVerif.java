package com.hebust.entity.mailVerif;

import java.io.Serializable;
import lombok.Data;

/**
 * mail_verif
 * @author 
 */
@Data
public class MailVerif implements Serializable {
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
    private String verifCode;

    private static final long serialVersionUID = 1L;
}