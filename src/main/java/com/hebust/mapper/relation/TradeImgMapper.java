package com.hebust.mapper.relation;

import com.hebust.entity.trade.TradeImg;

import java.util.List;

public interface TradeImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TradeImg record);

    int insertSelective(TradeImg record);

    TradeImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeImg record);

    int updateByPrimaryKey(TradeImg record);

    /**
     * 保持图片路径到服务器
     */
    int uploadImg(TradeImg img);

    /**
     * 通过tid查询与之对应的图片信息
     */
    List<String> queryImg(int tid);

    /**
     * 根据图片名称删除图片
     */
    int fakeDeleteImg(String img);
}