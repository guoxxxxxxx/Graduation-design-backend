package com.hebust.mapper;

import com.hebust.entity.alumni.AlumniDiscuss;
import com.hebust.entity.table.DiscussTable;

import java.util.List;

public interface AlumniDiscussMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlumniDiscuss record);

    int insertSelective(AlumniDiscuss record);

    AlumniDiscuss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlumniDiscuss record);

    int updateByPrimaryKey(AlumniDiscuss record);

    /**
     * 插入新的评论
     */
    int sendDiscuss(AlumniDiscuss discuss);

    /**
     * 模糊分页查询
     */
    List<AlumniDiscuss> queryDiscuss(String fuzzyParam, int page);

    /**
     * 查询符合条件的数量
     */
    int queryDiscussCount(String fuzzyParam);

    /**
     * 查询订单评论信息
     */
    List<DiscussTable> queryDiscussTable();

    /**
     * 通过主键删除评论信息
     */
    int fakeDeleteById(int id);

    /**
     * 查询评论信息数量
     */
    int queryDiscussCountIndex();
}