package com.hebust.mapper.relation;

import com.hebust.entity.lostProperty.LostDiscuss;
import com.hebust.entity.lostProperty.LostReply;

import java.util.List;

public interface LostDiscussMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LostDiscuss record);

    int insertSelective(LostDiscuss record);

    LostDiscuss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LostDiscuss record);

    int updateByPrimaryKey(LostDiscuss record);

    /**
     * 根据lid查询评论信息及其子评论
     */
    List<LostDiscuss> queryDiscussByLid(int lid);

    /**
     * 查询当前页面所拥有的评论数量
     */
    int queryDiscussCountByLid(int lid);

    /**
     * 添加新回复
     */
    int sendDiscuss(LostDiscuss discuss);
}