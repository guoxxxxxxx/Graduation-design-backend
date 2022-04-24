package com.hebust.mapper.relation;

import com.hebust.entity.table.ReplyTable;
import com.hebust.entity.trade.TradeReply;

import java.util.List;

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

    /**
     * 通过父id删除回复
     */
    int fakeDeleteByFatherId(int fid);

    /**
     * 查询回复数量 管理员
     */
    int queryReplyCountManager();

    /**
     * 通过自己的主键删除自己
     */
    int fakeDeleteById(int id);

    /**
     * 通过项目id删除回复
     */
    int fakeDeleteByItemId(int itemId);

    /**
     * 查询回复消息
     */
    List<ReplyTable> queryReplyTable();

}