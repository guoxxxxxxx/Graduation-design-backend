package com.hebust.mapper;

import com.hebust.entity.QueryCondition;
import com.hebust.entity.lostProperty.LostProperty;

import java.util.List;

public interface LostPropertyMapper {
    int deleteByPrimaryKey(Integer lid);

    int insert(LostProperty record);

    int insertSelective(LostProperty record);

    LostProperty selectByPrimaryKey(Integer lid);

    int updateByPrimaryKeySelective(LostProperty record);

    int updateByPrimaryKey(LostProperty record);

    /**
     * 添加新记录
     */
    int publishNewItem(LostProperty record);

    /**
     * 通过条件分页查询所需要的信息
     */
    List<LostProperty> queryByCondition(QueryCondition condition);

    /**
     * 通过条件查询符合条件的数量
     */
    int queryCountByCondition(QueryCondition condition);

    /**
     * 通过lid查询详细信息
     */
    LostProperty queryDetailsByLit(int lid);

    /**
     * 通过lid将项目标记为已完成
     */
    int updateIsAchieveByLid(int lid);

    /**
     * 伪删除信息
     */
    int fakeDeleteByLid(int lid);

    /**
     * 通过lid更新项目信息
     */
    int updateByLid(LostProperty property);

    /**
     * 查询失物招领数量
     */
    int queryCount();

    /**
     * 查询已经完成的项目数量
     */
    int queryAchieveCount();
}