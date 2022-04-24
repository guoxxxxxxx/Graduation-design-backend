package com.hebust.mapper.relation;

import com.hebust.entity.lostProperty.LostDiscuss;
import com.hebust.entity.lostProperty.LostReply;
import com.hebust.entity.table.DiscussTable;

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

    /**
     * 查询所有评论数量
     */
    int queryDiscussCount();

    /**
     * 删除评论信息通过父项目
     */
    int fakeDeleteDiscussByLid(int lid);

    /**
     * 查询评论信息
     */
    List<DiscussTable> queryDiscussTable();

    /**
     * 删除评论通过主键
     */
    int fakeDeleteDiscussById(int id);
}