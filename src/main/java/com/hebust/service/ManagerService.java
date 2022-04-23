package com.hebust.service;

import com.hebust.entity.item.ErrandTable;

import java.util.HashMap;
import java.util.List;

public interface ManagerService {

    /**
     * 查询跑腿的项目数量
     */
    int queryErrandItemCount();

    /**
     * 查询跑腿的评论和回复数量
     * @return
     */
    HashMap<String, Integer> queryErrandDisRepCount();

    /**
     * 查询跑腿订单的已完成，已接单，总订单信息
     */
    HashMap<String, Integer> queryErrandItemsStatusCount();

    /**
     * 查询所有跑腿项目
     * @return
     */
    List<ErrandTable> queryAllErrandItem();
}
