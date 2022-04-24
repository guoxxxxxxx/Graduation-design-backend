package com.hebust.mapper.relation;

import com.hebust.entity.study.StudyReply;
import com.hebust.entity.table.ReplyTable;

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

    /**
     * 通过sid伪删除与之关联的回复信息
     */
    int fakeDeleteReplyBySid(int sid);

    /**
     * 发表回复信息
     */
    int sendReply(StudyReply reply);

    /**
     * 查询未删除的回复数量
     */
    int queryReplyCount();

    /**
     * 通过自己的主键伪删除自己
     */
    int fakeDeleteRelpy(int id);

    /**
     * 通过父评论伪删除回复
     */
    int fakeDeleteReplyByFatherId(int fid);

    /**
     * 查询管理员界面所需回复信息
     */
    List<ReplyTable> queryReply();
}