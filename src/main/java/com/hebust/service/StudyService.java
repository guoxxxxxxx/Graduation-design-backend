package com.hebust.service;

import com.hebust.entity.QueryCondition;
import com.hebust.entity.study.Study;
import com.hebust.entity.study.StudyDiscuss;
import com.hebust.entity.study.StudyImg;
import com.hebust.entity.study.StudyReply;

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

    /**
     * 通过sid查询项目的详细信息 包括图片信息
     */
    Study selectDetailsBySid(int sid);

    /**
     * 通过sid查询该项目的所有评论信息
     */
    List<StudyDiscuss> selectDiscussBySid(int sid);

    /**
     * 通过sid查询当前界面所拥有的评论数量
     */
    int selectDiscussCountBySid(int sid);

    /**
     * 通过sid将完成状态改为1
     */
    int setAchieveBySid(int sid);

    /**
     * 伪删除记录
     */
    int fakeDeleteBySid(int sid);

    /**
     * 插入新的评价记录
     */
    int insertDiscuss(StudyDiscuss discuss);

    /**
     * 通过图片名称删除图片信息
     */
    int fakeDeleteImgByFilename(String filename);

    /**
     * 通过sid更新项目信息
     */
    int updateBySid(Study study);

    /**
     * 发送评论信息
     */
    int doSendReply(StudyReply reply);

    /**
     * 根据条件查询信息
     */
    List<Study> queryByCondition(QueryCondition condition);

    /**
     * 条件查询项目总数
     */
    int queryItemsCountByCondition(QueryCondition condition);
}
