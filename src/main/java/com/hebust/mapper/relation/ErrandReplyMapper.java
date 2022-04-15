package com.hebust.mapper.relation;

import com.hebust.entity.errand.ErrandDiscuss;
import com.hebust.entity.errand.ErrandReply;

import java.util.List;

public interface ErrandReplyMapper {

    /**
     * 通过parent_discuss_id查询所有评论信息
     */
    List<ErrandReply> selectAllByPdi(int pdi);

    /**
     * 向数据表中添加回复信息
     */
    int insertReply(ErrandReply reply);
}