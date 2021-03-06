package com.hebust.service;

import com.hebust.entity.QueryCondition;
import com.hebust.entity.errand.Errand;

import java.util.List;

public interface ErrandService {

    /**
     * 查询所有跑腿订单
     */
    List<Errand> selectAll();

    /**
     * 根据eid查询跑腿订单详细信息
     */
    Errand queryDetailsByEid(int eid);

    /**
     * 根据种类查询跑腿订单项目
     */
    List<Errand> queryItemByCategory(String category);

    /**
     * 添加新的跑腿订单
     */
    int addErrandItem(Errand errand);

    /**
     * 更新接单用户信息
     */
    int updateTakeOrdersUid(Errand errand);

    /**
     * 伪删除跑腿订单通过eid
     */
    int fakeDeleteItemByEid(int eid);

    /**
     * 通过eid更新跑腿项目订单
     */
    int updateErrandItemByEid(Errand errand);

    /**
     * 通过eid更新项目订单完成状态
     */
    int updateErrandIsAchieveStateByEid(int eid);

    /**
     * 条件查询信息
     */
    List<Errand> queryByCondition(QueryCondition condition);

    /**
     * 条件查询符合条件的项目数量
     */
    int queryCountByCondition(QueryCondition condition);

    /**
     * 查询跑腿订单的评论数量
     */
    int queryDiscussCount(int eid);

    /**
     * 通过eid伪删除跑腿信息表
     */
    int fakeDeleteItem(int eid);
}
