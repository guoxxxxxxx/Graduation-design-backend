package com.hebust.mapper.relation;

import com.hebust.entity.errand.ErrandDiscuss;

import java.util.List;

public interface ErrandDiscussMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ErrandDiscuss record);

    int insertSelective(ErrandDiscuss record);

    ErrandDiscuss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ErrandDiscuss record);

    int updateByPrimaryKey(ErrandDiscuss record);

    /**
     * 通过eid查询所有评论内容及其子内容
     */
    List<ErrandDiscuss> selectAllByEid(int eid);
}