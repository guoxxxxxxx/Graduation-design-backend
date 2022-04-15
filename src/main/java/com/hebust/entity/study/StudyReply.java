package com.hebust.entity.study;

import java.io.Serializable;
import java.util.Date;

import com.hebust.entity.user.SimplifyUser;
import lombok.Data;

/**
 * study_reply
 * @author 
 */
@Data
public class StudyReply implements Serializable {
    /**
     * 主键 自增
     */
    private Integer id;

    /**
     * 所属父评论id
     */
    private Integer parentDiscussId;

    /**
     * 发布回复用户id
     */
    private Integer commentUid;

    /**
     * 简要版发布回复信息的用户信息
     */
    private SimplifyUser commentUser;

    /**
     * 被回复用户id
     */
    private Integer targetUid;

    /**
     * 简要版被回复信息的用户信息
     */
    private SimplifyUser targetUser;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 发表评论的时间
     */
    private String createDate;

    /**
     * 是否删除
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}