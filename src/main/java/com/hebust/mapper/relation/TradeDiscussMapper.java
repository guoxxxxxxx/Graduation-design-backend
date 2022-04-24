package com.hebust.mapper.relation;

import com.hebust.entity.table.DiscussTable;
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

    /**
     * 查询评论数量
     */
    int queryDiscussCount(int tid);

    /**
     * 查询评论数量 管理员
     */
    int queryDiscussCountManager();

    /**
     * 通过项目id伪删除评论信息
     */
    int fakeDeleteDiscussItemId(int tid);

    /**
     * 管理员查询评论信息
     */
    List<DiscussTable> queryDiscussItemManager();

    /**
     * 通过主键删除评论
     */
    int fakeDeleteById(int id);
}