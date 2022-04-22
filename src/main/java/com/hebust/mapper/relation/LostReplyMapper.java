package com.hebust.mapper.relation;

import com.hebust.entity.lostProperty.LostReply;

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
}