package com.hebust.mapper.relation;

import com.hebust.entity.alumni.AlumniReply;

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
}