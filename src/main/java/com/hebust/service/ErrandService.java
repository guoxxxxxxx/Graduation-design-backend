package com.hebust.service;

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
}
