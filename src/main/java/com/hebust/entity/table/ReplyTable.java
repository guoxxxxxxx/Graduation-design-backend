package com.hebust.entity.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyTable {

    /**
     * 当前回复主键
     */
    private int id;

    /**
     * 发布人id
     */
    private int pubUser;

    /**
     * 发布人姓名
     */
    private String pubUserName;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 回复简短内容
     */
    private String shortContent;

    /**
     * 回复时间
     */
    private String date;

    /**
     * 被回复人id
     */
    private int targetId;

    /**
     * 被回复人姓名
     */
    private String targetName;

    private static final long serialVersionUID = 1L;

}
