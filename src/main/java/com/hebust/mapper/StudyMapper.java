package com.hebust.mapper;

import com.hebust.entity.study.Study;

import java.util.List;

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
}