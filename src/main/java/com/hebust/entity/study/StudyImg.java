package com.hebust.entity.study;

import java.io.Serializable;
import lombok.Data;

/**
 * study_img
 * @author 
 */
@Data
public class StudyImg implements Serializable {
    /**
     * 主键 自增
     */
    private Integer id;

    /**
     * 学习表的外键
     */
    private Integer sid;

    /**
     * 图片保存的地址
     */
    private String imgSrc;

    /**
     * 是否删除
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}