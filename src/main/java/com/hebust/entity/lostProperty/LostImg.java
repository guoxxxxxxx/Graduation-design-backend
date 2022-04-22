package com.hebust.entity.lostProperty;

import java.io.Serializable;
import lombok.Data;

/**
 * lost_img
 * @author 
 */
@Data
public class LostImg implements Serializable {
    /**
     * 主键 自增
     */
    private Integer id;

    /**
     * 失物招领表的外键
     */
    private Integer lid;

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