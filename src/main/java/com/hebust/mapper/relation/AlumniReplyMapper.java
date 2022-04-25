package com.hebust.mapper.relation;

import com.hebust.entity.alumni.AlumniReply;
import com.hebust.entity.table.ReplyTable;

import java.util.List;

public interface AlumniReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlumniReply record);

    int insertSelective(AlumniReply record);

    AlumniReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlumniReply record);

    int updateByPrimaryKey(AlumniReply record);

    /**
     * 插入新的回复信息
     */
    int sendReply(AlumniReply reply);

    /**
     * 根据评论信息的主键查询与之对应的回复信息
     */
    List<AlumniReply> queryReply(int did);

    /**
     * 通过父评论删除信息
     */
    int fakeDeleteByFatherId(int fid);

    /**
     * 通过主键删除评论
     */
    int fakeDeleteById(int id);

    /**
     * 查询所需要的评论信息
     */
    List<ReplyTable> queryReplyTable();

    /**
     * 查询回复数量
     */
    int queryReplyCountIndex();
}