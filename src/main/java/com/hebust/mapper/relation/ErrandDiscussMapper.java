package com.hebust.mapper.relation;

import com.hebust.entity.errand.ErrandDiscuss;
import com.hebust.entity.table.DiscussTable;

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

    /**
     * 查询跑腿评论总数量
     */
    int queryDiscussAllCount();

    /**
     * 通过eid伪删除评论
     */
    int fakeDeleteDiscussByEid(int eid);

    /**
     * 通过id伪删除评论
     */
    int fakeDeleteDiscussById(int id);

    /**
     * 查询评论信息 管理员端
     */
    List<DiscussTable> queryDiscussTable();
}