package com.hebust.entity.errand;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
     * 被评论用户
     */
    private Integer targetUid;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 发表评论的日期
     */
    private Date pubdate;

    /**
     * 发表评论的时间
     */
    private Date pubtime;

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
    private List<ErrandReply> errandReplyList;

    private static final long serialVersionUID = 1L;
}