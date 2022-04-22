package com.hebust.entity.alumni;

import java.io.Serializable;
import java.util.Date;

import com.hebust.entity.user.SimplifyUser;
import lombok.Data;

/**
 * alumni_reply
 * @author 
 */
@Data
public class AlumniReply implements Serializable {
    /**
     * 主键自增
     */
    private Integer id;

    /**
     * 回复所属的父评论
     */
    private int parentDiscussId;

    /**
     * 发布回复的用户
     */
    private Integer commentUid;

    /**
     * 发表回复用户的详细信息
     */
    private SimplifyUser commentUser;

    /**
     * 被回复的用户
     */
    private Integer targetUid;

    /**
     * 被回复信息的用户详细信息
     */
    private SimplifyUser targetUser;

    /**
     * 发布回复的内容
     */
    private String content;

    /**
     * 发布的时间
     */
    private String createDate;

    /**
     * 是否删除
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}