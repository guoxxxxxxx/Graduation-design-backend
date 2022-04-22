package com.hebust.service;

import com.hebust.entity.alumni.AlumniDiscuss;
import com.hebust.entity.alumni.AlumniReply;

import java.util.List;

public interface AlumniService {

    /**
     * 添加新的评论
     */
    int sendDiscuss(AlumniDiscuss discuss);

    /**
     * 插入新的回复信息
     */
    int sendReply(AlumniReply reply);

    /**
     * 根据参数分页查询评论信息
     */
    List<AlumniDiscuss> queryDiscuss(String fuzzyParam, int page);

    /**
     * 查询符合条件的数量
     */
    int queryDiscussCount(String fuzzyParam);
}
