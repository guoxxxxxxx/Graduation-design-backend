package com.hebust.mapper.relation;

import com.hebust.entity.trade.TradeDiscuss;

import java.util.List;

public interface TradeDiscussMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TradeDiscuss record);

    int insertSelective(TradeDiscuss record);

    TradeDiscuss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeDiscuss record);

    int updateByPrimaryKey(TradeDiscuss record);

    /**
     * 查询评论和其对应的子评论
     */
    List<TradeDiscuss> queryDiscussByTid(int tid);

    /**
     * 添加新的评论信息
     */
    int sendDiscuss(TradeDiscuss discuss);
}