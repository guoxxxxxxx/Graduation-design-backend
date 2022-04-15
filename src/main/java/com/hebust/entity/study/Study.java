package com.hebust.entity.study;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hebust.entity.user.User;
import lombok.Data;

/**
 * study
 * @author 
 */
@Data
public class Study implements Serializable {
    /**
     * 主键
     */
    private Integer sid;

    /**
     * 发帖用户, 外：user(uid)
     */
    private Integer uid;

    /**
     * 发布该信息的用户详细信息
     */
    private User pubUser;

    /**
     * 类别,可选项为: 数学, 物理, 英语, 其他
     */
    private String category;

    /**
     * 题目
     */
    private String title;

    /**
     * 详细内容
     */
    private String details;

    /**
     * 提交时间
     */
    private Date pubdate;

    /**
     * 是否已解决, 1:已解决 0:未解决 默认为0
     */
    private Integer isAchieve;

    /**
     * 是否删除, 1: 删除; 0: 未删除; 默认为0
     */
    private Integer isDelete;

    /**
     * 该学习项目的图片信息
     */
    private List<String> imgUrls;

    private static final long serialVersionUID = 1L;
}