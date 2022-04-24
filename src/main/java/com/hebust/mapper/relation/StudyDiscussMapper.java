package com.hebust.mapper.relation;

import com.hebust.entity.study.StudyDiscuss;
import com.hebust.entity.table.DiscussTable;

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

    /**
     * 伪删除discuss表中的数据通过sid
     */
    int fakeDeleteDiscussBySid(int sid);

    /**
     * 发送评论信息
     */
    int insertDiscuss(StudyDiscuss discuss);

    /**
     * 查询未删除的评论信息数量
     */
    int queryDiscussCount();

    /**
     * 查询管理员界面所需评论信息
     */
    List<DiscussTable> queryDiscussTable();

    /**
     * 通过主键伪删除评论
     */
    int fakeDeleteDiscuss(int id);

}