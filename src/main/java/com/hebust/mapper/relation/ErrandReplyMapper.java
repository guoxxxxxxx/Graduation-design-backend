package com.hebust.mapper.relation;

import com.hebust.entity.errand.ErrandDiscuss;
import com.hebust.entity.errand.ErrandReply;
import com.hebust.entity.table.ReplyTable;

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

    /**
     * 查询回复信息的数量
     */
    int queryReplyCount();

    /**
     * 通过id伪删除回复信息
     */
    int fakeDeleteById(int id);

    /**
     * 通过父id删除回复信息
     */
    int fakeDeleteByParentId(int pid);

    /**
     * 通过eid删除回复信息
     */
    int fakeDeleteByEid(int eid);

    /**
     * 查询回复信息 管理员
     */
    List<ReplyTable> queryReplyManager();
}