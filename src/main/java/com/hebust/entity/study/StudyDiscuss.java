package com.hebust.entity.study;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hebust.entity.user.SimplifyUser;
import lombok.Data;

/**
 * study_discuss
 * @author 
 */
@Data
public class StudyDiscuss implements Serializable {
    /**
     * 主键 自增
     */
    private Integer id;

    /**
     * 该评论所属的父项目
     */
    private Integer sid;

    /**
     * 发布评论的用户
     */
    private Integer commentUid;

    /**
     * 发布评论用户的详细信息
     */
    private SimplifyUser commentUser;

    /**
     * 评论的内容
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
    private List<StudyReply> childrenList;

    private static final long serialVersionUID = 1L;
}