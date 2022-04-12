package com.hebust.service.relation;

import com.hebust.entity.errand.ErrandDiscuss;

import java.util.List;

public interface ErrandDiscussService {

    /**
     * 通过eid查询此订单的所有评论内容
     */
    List<ErrandDiscuss> selectAllDiscussByEid(int eid);
}
