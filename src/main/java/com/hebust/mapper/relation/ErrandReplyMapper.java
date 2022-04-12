package com.hebust.mapper.relation;

import com.hebust.entity.errand.ErrandReply;

import java.util.List;

public interface ErrandReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ErrandReply record);

    int insertSelective(ErrandReply record);

    ErrandReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ErrandReply record);

    int updateByPrimaryKey(ErrandReply record);

    /**
     * 通过parent_discuss_id查询所有评论信息
     */
    List<ErrandReply> selectAllByPdi(int pdi);
}