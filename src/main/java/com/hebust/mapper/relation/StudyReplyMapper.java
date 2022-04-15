package com.hebust.mapper.relation;

import com.hebust.entity.study.StudyReply;

import java.util.List;

public interface StudyReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudyReply record);

    int insertSelective(StudyReply record);

    StudyReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudyReply record);

    int updateByPrimaryKey(StudyReply record);

    /**
     * 通过父评论id查询与之对应的父评论信息
     */
    List<StudyReply> selectReplyByFatherId(int fid);
}