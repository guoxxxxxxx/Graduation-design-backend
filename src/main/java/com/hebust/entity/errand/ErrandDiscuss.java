package com.hebust.entity.errand;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hebust.entity.user.SimplifyUser;
import lombok.Data;

/**
 * errand_discuss
 * @author 
 */
@Data
public class ErrandDiscuss implements Serializable {
    /**
     * 主键自增
     */
    private Integer id;

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
     * 被评论用户详细信息
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

    /**
     * 该评论所属的跑腿订单
     */
    private Integer eid;

    /**
     * 该评论的子评论内容
     */
    private List<ErrandReply> childrenList;

    private static final long serialVersionUID = 1L;
}