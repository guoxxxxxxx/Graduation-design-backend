package com.hebust.service;

import com.hebust.entity.QueryCondition;
import com.hebust.entity.trade.Trade;
import com.hebust.entity.trade.TradeDiscuss;
import com.hebust.entity.trade.TradeImg;
import com.hebust.entity.trade.TradeReply;

import java.util.List;

public interface TradeService {

    /**
     * 图片上传功能
     */
    int uploadImg(TradeImg img);

    /**
     * 向交易表中添加新的数据
     */
    int addNewItem(Trade trade);

    /**
     * 通过tid查询与之对应的相关信息
     */
    Trade queryDetailsByTid(int tid);

    /**
     * 通过条件查询所有符合条件的仙姑
     */
    List<Trade> queryItemsByCondition(QueryCondition condition);

    /**
     * 预购项目
     */
    int wantToBuy(int tid, int tuid);

    /**
     * 查询符合条件呢的订单的总数量
     */
    int queryItemsCountByCondition(QueryCondition condition);

    /**
     * 更新项目信息
     */
    int updateByTid(Trade trade);

    /**
     * 通过tid查询与之对应的图片信息
     */
    List<String> queryImgByTid(int tid);

    /**
     * 通过图片名称伪删除图片
     */
    int fakeDeleteImg(String img);

    /**
     * 通过tid查询评论信息 分页
     */
    List<TradeDiscuss> queryDiscuss(int tid, int page);

    /**
     * 添加评论信息
     */
    int sendDiscuss(TradeDiscuss discuss);

    /**
     * 添加回复信息
     */
    int sendReply(TradeReply reply);

    /**
     * 通过tid伪删除信息
     */
    int fakeDeleteItem(int tid);

    /**
     * 查询评论数量
     */
    int queryDiscussCount(int tid);
}
