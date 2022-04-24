package com.hebust.mapper.relation;

import com.hebust.entity.lostProperty.LostReply;
import com.hebust.entity.table.ReplyTable;

import java.util.List;

public interface LostReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LostReply record);

    int insertSelective(LostReply record);

    LostReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LostReply record);

    int updateByPrimaryKey(LostReply record);

    /**
     * 根据父id查询对应的回复信息
     */
    List<LostReply> queryReply(int fatherId);

    /**
     * 添加回复
     */
    int doSendReply(LostReply reply);

    /**
     * 查询回复数量
     */
    int queryReplyCount();

    /**
     * 通过项目id删除回复消息
     */
    int fakeDeleteReplyByLid(int lid);

    /**
     * 通过父id删除回复信息
     */
    int fakeDeleteReplyByFatherId(int fid);

    /**
     * 查询回复信息
     */
    List<ReplyTable> queryReplyTable();

    /**
     * 通过主键删除回复信息
     */
    int fakeDeleteById(int id);
}