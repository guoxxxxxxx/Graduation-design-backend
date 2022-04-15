package com.hebust.service;

import com.hebust.entity.study.Study;

import java.util.List;

public interface StudyService {

    /**
     * 向数据库中插入新的数据
     */
    int addNewItem(Study study);

    /**
     * 查询所有学习项目 包括发布人信息
     */
    List<Study> selectAll(int page, int pageSize);

    /**
     * 查询所有项目数量
     */
    int selectAllItemCount();

}
