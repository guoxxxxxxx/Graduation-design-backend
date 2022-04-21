package com.hebust.mapper;

import com.hebust.entity.QueryCondition;
import com.hebust.entity.errand.Errand;

import java.util.List;

public interface ErrandMapper {
    int deleteByPrimaryKey(Integer eid);

    int insert(Errand record);

    /**
     * 添加新的跑腿订单
     */
    int insertSelective(Errand record);

    Errand selectByPrimaryKey(Integer eid);

    /**
     * 更新接单用户ID
     */
    int updateByPrimaryKeySelective(Errand record);

    int updateByPrimaryKey(Errand record);

    /**
     * 查询所有订单信息
     * 及订单所对应的图片信息,发布用户详细信息,接单用户详细信息
     */
    List<Errand> selectAll();

    /**
     * 根据eid查询跑腿订单详细信息
     */
    Errand selectDetailsByEid(int eid);

    /**
     * 根据种类查询订单项目
     */
    List<Errand> selectItemByCategory(String category);


    /**
     * 通过eid伪删除跑腿订单
     */
    int fakeDeleteItemByEid(int eid);

    /**
     * 通过eid更新跑腿订单信息
     */
    int updateErrandItemByEid(Errand errand);

    /**
     * 根据eid设置订单完成状态
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
     * 通过eid伪删除跑腿信息表
     */
    int fakeDeleteItem(int eid);
}