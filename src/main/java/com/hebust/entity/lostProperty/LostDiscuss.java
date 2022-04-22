package com.hebust.entity.lostProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hebust.entity.user.SimplifyUser;
import com.hebust.entity.user.User;
import lombok.Data;

/**
 * lost_discuss
 * @author 
 */
@Data
public class LostDiscuss implements Serializable {
    /**
     * 主键自增
     */
    private Integer id;

    /**
     * 该评论所属的交易订单
     */
    private Integer lid;

    /**
     * 发布评论的用户id
     */
    private Integer commentUid;

    /**
     * 发布评论用户的简述信息
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
     * 当前评论对应的子评论
     */
    private List<LostReply> childrenList;

    private static final long serialVersionUID = 1L;
}