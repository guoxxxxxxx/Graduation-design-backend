package com.hebust.service;

import com.hebust.entity.study.Study;
import com.hebust.entity.study.StudyImg;

import java.util.List;

public interface StudyService {

    /**
     * 向数据库中插入新的数据
     */
    int addNewItem(Study study);

    /**
     * 查询所有学习项目 包括发布人信息 以及与其对应的图片信息
     */
    List<Study> selectAll(int page, int pageSize);

    /**
     * 查询所有项目数量
     */
    int selectAllItemCount();

    /**
     * 通过sid查询与之对应的未删除的图片
     */
    List<String> selectAllImgBySid(int sid);

    /**
     * 向数据库中插入图片路径信息
     */
    int insertImage(StudyImg record);
}
