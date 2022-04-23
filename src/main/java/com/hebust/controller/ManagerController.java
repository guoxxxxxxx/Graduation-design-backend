package com.hebust.controller;

import com.hebust.entity.item.ErrandTable;
import com.hebust.entity.other.ManagerVO;
import com.hebust.service.ManagerService;
import com.hebust.utils.PreComputeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    /**
     * 查询跑腿项目数量和评论数量
     */
    @RequestMapping("/errand/queryItemDisCount")
    public ManagerVO queryErrandItemDisCount(){
        int itemCount = managerService.queryErrandItemCount();
        HashMap<String, Integer> dis_rep_map = managerService.queryErrandDisRepCount();
        dis_rep_map.put("itemCount", itemCount);
        return ManagerVO.SUCCESS(dis_rep_map);
    }

    /**
     * 查询数据总览界面数据
     */
    @RequestMapping("/errand/queryPercentage")
    public ManagerVO queryErrandPercentage(){
        HashMap<String, Integer> map = managerService.queryErrandItemsStatusCount();
        HashMap<String, String> compute = PreComputeUtils.compute(map.get("all"), map.get("all"), map.get("take"), map.get("achieve"));
        return ManagerVO.SUCCESS(compute);
    }

    /**
     * 查询所有项目
     */
    @RequestMapping("/errand/queryAllItem")
    public ManagerVO queryErrandAllItem(){
        List<ErrandTable> errandTables = managerService.queryAllErrandItem();
        return ManagerVO.SUCCESS(errandTables);
    }
}
