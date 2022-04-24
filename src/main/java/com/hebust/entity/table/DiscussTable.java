package com.hebust.entity.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussTable {

    /**
     * 自身id
     */
    private int id;

    /**
     * 评论所属订单id
     */
    private int parentId;

    /**
     * 评论人id
     */
    private int pubUserId;

    /**
     * 评论人姓名
     */
    private String pubUserName;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 精简评论内容
     */
    private String shortContent;

    /**
     * 评论时间
     */
    private String date;

    private static final long serialVersionUID = 1L;

}
