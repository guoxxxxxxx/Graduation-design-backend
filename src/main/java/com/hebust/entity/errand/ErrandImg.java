package com.hebust.entity.errand;

import java.io.Serializable;
import lombok.Data;

/**
 * errand_img
 * @author 
 */
@Data
public class ErrandImg implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * errand的外键
     */
    private Integer eid;

    /**
     * 图片路径
     */
    private String imgSrc;

    private static final long serialVersionUID = 1L;
}