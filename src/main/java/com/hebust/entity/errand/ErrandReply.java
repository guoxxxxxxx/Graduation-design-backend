package com.hebust.entity.errand;

import java.io.Serializable;
import java.util.Date;

import com.hebust.entity.user.SimplifyUser;
import lombok.Data;

/**
 * errand_reply
 * @author 
 */
@Data
public class ErrandReply implements Serializable {
    /**
     * 主键自增
     */
    private Integer id;

    /**
     * 所属父评论id
     */
    private Integer parentDiscussId;

    /**
     * 发布评论的用户id
     */
    private Integer commentUid;

    /**
     * 发布评论用户的详细信息
     */
    private SimplifyUser commentUser;

    /**
     * 被评论用户
     */
    private Integer targetUid;

    /**
     * 被评论用户的详细信息
     */
    private SimplifyUser targetUser;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 发表评论的日期时间
     */
    private String createDate;

    /**
     * 是否删除
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}