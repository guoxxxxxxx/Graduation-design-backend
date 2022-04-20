package com.hebust.entity.trade;

import java.io.Serializable;
import lombok.Data;

/**
 * trade_img
 * @author 
 */
@Data
public class TradeImg implements Serializable {
    /**
     * 主键 自增
     */
    private Integer id;

    /**
     * 交易表的外键
     */
    private Integer tid;

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