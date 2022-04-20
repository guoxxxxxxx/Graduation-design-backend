package com.hebust.entity.trade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hebust.entity.study.StudyReply;
import com.hebust.entity.user.SimplifyUser;
import lombok.Data;

/**
 * trade_discuss
 * @author 
 */
@Data
public class TradeDiscuss implements Serializable {
    /**
     * 主键自增
     */
    private Integer id;

    /**
     * 该评论所属的交易订单
     */
    private Integer tid;

    /**
     * 发布评论的用户id
     */
    private Integer commentUid;

    /**
     * 发布评论用户的相信信息
     */
    private SimplifyUser commentUser;

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

    /**
     * 与该评论对应的子回复信息
     */
    private List<TradeReply> childrenList;


    private static final long serialVersionUID = 1L;
}