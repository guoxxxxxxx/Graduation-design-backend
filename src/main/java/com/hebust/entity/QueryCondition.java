package com.hebust.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询属性对象, 用于保存查询属性
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryCondition {

    /**
     * 所属种类
     */
    private String category;

    /**
     * 查询第几页
     */
    private int page;

    /**
     * 模糊查询参数
     */
    private String fuzzyParam;

    /**
     * 是否隐藏已完成订单, 1是隐藏; 0是显示
     */
    private int isHiddenAchieve;

    /**
     * 是否显示已接单订单, 1是隐藏; 0是显示
     */
    private int isHiddenTakeOrders;


}
