package com.hebust.service;

import com.hebust.entity.QueryCondition;
import com.hebust.entity.lostProperty.LostDiscuss;
import com.hebust.entity.lostProperty.LostImg;
import com.hebust.entity.lostProperty.LostProperty;
import com.hebust.entity.lostProperty.LostReply;

import java.util.List;

public interface LostPropertyService {

    /**
     * 添加新的记录
     * @param property
     */
    int publishNewItem(LostProperty property);

    /**
     * 添加新的图片到数据库中
     * @param img
     * @return
     */
    int uploadImg(LostImg img);

    /**
     * 通过条件查询所需要的项目信息
     */
    List<LostProperty> queryByCondition(QueryCondition condition);

    /**
     * 通过条件查询符合条件的数量
     */
    int queryCountByCondition(QueryCondition condition);

    /**
     * 通过lid查询详细信息
     */
    LostProperty queryDetailsByLid(int lid);

    /**
     * 通过lid标记为已完成
     */
    int updateIsAchieveByLid(int lid);

    /**
     * 伪删除信息
     */
    int fakeDeleteByLid(int lid);

    /**
     * 通过lid查询与之对应的图片信息
     */
    List<String> queryImgByLid(int lid);

    /**
     * 伪删除图片
     */
    int fakeDeleteImg(String imgSrc);

    /**
     * 通过lid更改项目信息
     */
    int updateByLid(LostProperty property);

    /**
     * 查询当前页面所有的评论数量
     */
    int queryDiscussCountByLid(int lid);

    /**
     * 发送评论
     */
    int sendDiscuss(LostDiscuss discuss);

    /**
     * 发送回复
     */
    int doSendReply(LostReply reply);

    /**
     * 根据id查询评论及其子评论
     * @return
     */
    List<LostDiscuss> queryDiscussById(int lid, int page);
}
