package com.hebust.mapper;

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

    int updateByPrimaryKeySelective(Errand record);

    int updateByPrimaryKey(Errand record);

    /**
     * 查询所有订单信息及订单所对应的图片信息
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
}