package com.hebust.entity.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemTable {
    /**
     * 日期
     */
    private String date;

    /**
     * 发布人
     */
    private String pubUser;

    /**
     * 标题
     */
    private String title;


    /**
     * 赏金
     */
    private String money;

    /**
     * 接单人
     */
    private String takeOrdersUser;

    /**
     * 状态
     */
    private String status;

    /**
     * 当前项目主键
     */
    private int id;

    /**
     * 是否已完成
     */
    private int isAchieve;

    /**
     * 所属种类
     */
    private String category;

    private static final long serialVersionUID = 1L;

}
