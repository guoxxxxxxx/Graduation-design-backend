package com.hebust.mapper.relation;

import com.hebust.entity.trade.TradeReply;

public interface TradeReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TradeReply record);

    int insertSelective(TradeReply record);

    TradeReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeReply record);

    int updateByPrimaryKey(TradeReply record);

    /**
     * 查找完整的回复信息
     */
    TradeReply queryReply(int did);

    /**
     * 新增回复信息
     */
    int doSendReply(TradeReply reply);
}