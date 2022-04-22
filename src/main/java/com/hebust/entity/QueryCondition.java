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
     * 是否隐藏已完成订单, true是隐藏; false是显示
     */
    private int isHiddenAchieve;

    /**
     * 是否显示已接单订单, true是隐藏; false是显示
     */
    private int isHiddenTakeOrders;

    /**
     * 检测属性是否为空，若为空则全部替换为默认值
     */
    public static QueryCondition checkCondition(QueryCondition condition){
        if (condition.getCategory() == null){
            condition.setCategory("");
        }
        if (condition.getFuzzyParam() == null){
            condition.setFuzzyParam("");
        }
        if (condition.getPage() == 0){
            condition.setPage(1);
        }
        return condition;
    }
}
