package com.hebust.mapper.relation;

import com.hebust.entity.study.StudyDiscuss;

import java.util.List;

public interface StudyDiscussMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudyDiscuss record);

    int insertSelective(StudyDiscuss record);

    StudyDiscuss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudyDiscuss record);

    int updateByPrimaryKey(StudyDiscuss record);

    /**
     * 通过sid查询与之对应的所有信息及其子评论
     */
    List<StudyDiscuss> selectDiscussBySid(int sid);

    /**
     * 通过sid查询当前界面评论信息的数量
     */
    int selectDiscussCountBySid(int sid);
}