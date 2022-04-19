package com.hebust.mapper.relation;

import com.hebust.entity.errand.ErrandDiscuss;

import java.util.List;

public interface ErrandDiscussMapper {
    /**
     * 通过eid查询所有评论内容及其子内容
     */
    List<ErrandDiscuss> selectAllByEid(int eid);

    /**
     * 插入新的评论
     */
    int insertDiscuss(ErrandDiscuss discuss);

    /**
     * 查询跑腿评论的数量
     */
    int queryDiscussCount(int eid);
}