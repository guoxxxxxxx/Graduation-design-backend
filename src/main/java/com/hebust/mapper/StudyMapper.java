package com.hebust.mapper;

import com.hebust.entity.QueryCondition;
import com.hebust.entity.study.Study;
import com.hebust.entity.table.ItemTable;

import java.util.List;
import java.util.concurrent.locks.Condition;

public interface StudyMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Study record);

    int insertSelective(Study record);

    /**
     * 通过sid查询项目的详细信息
     */
    Study selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Study record);

    int updateByPrimaryKey(Study record);

    /**
     * 查询所有学习项目 并查询用户信息
     */
    List<Study> selectAll();

    /**
     * 查询所有学习项目数量
     */
    int selectAllItemCount();

    /**
     * 通过sid将完成状态改为完成
     */
    int setAchieveBySid(int sid);

    /**
     * 通过sid伪删除订单 study表
     */
    int fakeDeleteBySid(int sid);

    /**
     * 通过sid更新信息
     */
    int updateBySid(Study study);

    /**
     * 根据查询条件来条件查询信息
     */
    List<Study> queryByCondition(QueryCondition condition);

    /**
     * 条件查询项目总数
     */
    int queryItemsCountByCondition(QueryCondition condition);

    /**
     * 查询学习订单的数量
     */
    int queryCount();

    /**
     * 查询已完成的订单数量
     */
    int queryAchieveCount();

    /**
     * 查询所有项目 管理员
     */
    List<ItemTable> queryAllItemManager();
}