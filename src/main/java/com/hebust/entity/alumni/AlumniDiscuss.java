package com.hebust.entity.alumni;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hebust.entity.user.SimplifyUser;
import lombok.Data;

/**
 * alumni_discuss
 * @author 
 */
@Data
public class AlumniDiscuss implements Serializable {
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
     * 发布的评论内容
     */
    private String content;

    /**
     * 发布评论的时间
     */
    private String createDate;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 评论所依赖的回复信息
     */
    List<AlumniReply> childrenList;

    private static final long serialVersionUID = 1L;
}