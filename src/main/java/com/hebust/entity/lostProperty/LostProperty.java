package com.hebust.entity.lostProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hebust.entity.user.User;
import lombok.Data;

/**
 * lost_property
 * @author 
 */
@Data
public class LostProperty implements Serializable {
    /**
     * 主键自增
     */
    private Integer lid;

    /**
     * 发布信息的用户
     */
    private Integer uid;

    /**
     * 发布信息用户的详细信息
     */
    private User pubUser;

    /**
     * 类别, 可选项有：找物品、找失主、其他
     */
    private String category;

    /**
     * 题目
     */
    private String title;

    /**
     * 详细说明
     */
    private String details;

    /**
     * 发布日期
     */
    private String pubdate;

    /**
     * 是否已删除
     */
    private Integer isDelete;

    /**
     * 是否已完成
     */
    private Integer isAchieve;

    /**
     * 所要上传的图片
     */
    private List<String> imgUrls;

    private static final long serialVersionUID = 1L;
}