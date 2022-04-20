package com.hebust.entity.trade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hebust.entity.user.User;
import lombok.Data;

/**
 * trade
 * @author 
 */
@Data
public class Trade implements Serializable {
    /**
     * 主键
     */
    private Integer tid;

    /**
     * 发布信息的用户 外: user(uid)
     */
    private Integer uid;

    /**
     * 发布信息用户的详细信息
     */
    private User pubUser;

    /**
     * 购买该商品的用户
     */
    private Integer tuid;

    /**
     * 购买用户的详细信息
     */
    private User takeOrdersUser;

    /**
     * 类别, 选项为: 出行工具, 书本资料, 生活用品和其他
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
     * 商品的价格
     */
    private Double money;

    /**
     * 发布日期
     */
    private String pubdate;

    /**
     * 是否删除, 1: 删除; 0: 未删除; 默认为0
     */
    private Integer isDelete;

    /**
     * 是否已经完成订单
     */
    private Integer isAchieve;

    /**
     * 存放图片的信息
     */
    private List<String> imgUrls;

    private static final long serialVersionUID = 1L;
}