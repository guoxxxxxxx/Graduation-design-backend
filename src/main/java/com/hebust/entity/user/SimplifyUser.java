package com.hebust.entity.user;

import lombok.Data;

/**
 * 简化版的User实体 用于存储评论的信息
 */
@Data
public class SimplifyUser {
    /**
     * 用户id
     */
    private int id;

    /**
     * 用户名称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String avatar;

}
