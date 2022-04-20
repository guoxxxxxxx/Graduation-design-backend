package com.hebust.mapper;

import com.hebust.entity.QueryCondition;
import com.hebust.entity.trade.Trade;

import java.util.List;

public interface TradeMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Trade record);

    int insertSelective(Trade record);

    Trade selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Trade record);

    int updateByPrimaryKey(Trade record);

    /**
     * 添加新的项目
     */
    int addNewItem(Trade trade);

    /**
     * 根据条件查询所需信息
     */
    List<Trade> queryByCondition(QueryCondition condition);

    /**
     * 根据tid查询详细信息
     */
    Trade queryDetailsByTid(int tid);

    /**
     * 预定订单
     */
    int wantToBuy(int tid, int tuid);

    /**
     * 查询符合条件项目的总数量
     */
    int queryItemsCountByCondition(QueryCondition condition);

    /**
     * 更新项目信息
     */
    int updateByTid(Trade trade);

    /**
     * 通过tid伪删除信息
     */
    int fakeDeleteItem(int tid);
}