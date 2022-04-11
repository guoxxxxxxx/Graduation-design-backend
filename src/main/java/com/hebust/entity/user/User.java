package com.hebust.entity.user;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    /**
     * id
     */
    private Integer uid;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别, 仅有(男,女,保密)三个值, 默认为保密
     */
    private String sex;

    /**
     * 生日 格式为yyyy-MM-dd
     */
    private Date birthday;

    /**
     * 电话号码, 固定长度为11位
     */
    private String phone;

    /**
     * 所属院系
     */
    private String faculty;

    /**
     * 所属年级, 例如: 2018
     */
    private String grade;

    /**
     * 所属专业
     */
    private String major;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * qq号
     */
    private String qq;

    /**
     * 是否删除, 1: 删除; 0: 未删除; 默认为0
     */
    private Integer isDelete;

    /**
     * 用户登录密码
     */
    private String password;

    /**
     * 用户头像路径
     */
    private String avatarPath;

    private static final long serialVersionUID = 1L;
}