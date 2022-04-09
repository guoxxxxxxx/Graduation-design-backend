package com.hebust.entity.errand;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hebust.entity.user.User;
import lombok.Data;

/**
 * errand
 * @author 
 */
@Data
public class Errand implements Serializable {
    /**
     * 主键
     */
    private Integer eid;

    /**
     * 发布订单用户信息 外: user(uid)
     */
    private Integer uid;

    /**
     * 接单用户信息 外: user(uid)
     */
    private Integer euid;

    /**
     * 订单信息
     */
    private String title;

    /**
     * 订单费用, 默认为0
     */
    private Double money;

    /**
     * 是否已完成, 0: 未完成; 1: 已完成;
     */
    private Integer isAchieve;

    /**
     * 类别, 可选值为(快递, 外卖, 打水, 其他)
     */
    private String category;

    /**
     * 发布日期
     */
    private Date pubdate;

    /**
     * 截止日期
     */
    private Date deadline;

    /**
     * 是否删除, 1: 删除; 0: 未删除; 默认为0
     */
    private Integer isDelete;

    /**
     * 详细信息
     */
    private String details;

    /**
     * 该跑腿订单的图片信息
     */
    private List<ErrandImg> imgUrls;

    /**
     * 该跑腿订单发布信息的用户信息
     */
    private User pubUser;

    private static final long serialVersionUID = 1L;
}