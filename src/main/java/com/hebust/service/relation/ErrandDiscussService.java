package com.hebust.service.relation;

import com.hebust.entity.errand.ErrandDiscuss;
import com.hebust.entity.errand.ErrandReply;

import java.util.List;

public interface ErrandDiscussService {

    /**
     * 通过eid查询此订单的所有评论内容
     */
    List<ErrandDiscuss> selectAllDiscussByEid(int eid);

    /**
     * 插入新的评论内容
     */
    int insertDiscuss(ErrandDiscuss discuss);

    /**
     * 插入新的回复消息
     */
    int insertReply(ErrandReply reply);
}
