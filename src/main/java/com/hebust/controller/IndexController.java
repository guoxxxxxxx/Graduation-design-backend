package com.hebust.controller;

import com.hebust.entity.other.IndexVO;
import com.hebust.entity.other.ManagerVO;
import com.hebust.entity.other.Percentage;
import com.hebust.mapper.relation.ErrandDiscussMapper;
import com.hebust.mapper.relation.ErrandReplyMapper;
import com.hebust.service.ErrandService;
import com.hebust.service.IndexService;
import com.hebust.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 主页的controller
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    IndexService indexService;
    @Autowired
    ManagerService managerService;

    @RequestMapping("/queryPercentage")
    public IndexVO queryPercentage(){
        Percentage percentage = indexService.queryPercentage();
        return IndexVO.SUCCESS(percentage);
    }

    /**
     * 查询项目总数 和 评论总数
     */
    @RequestMapping("/queryAllCount")
    public IndexVO queryItemCount(){
        // 查询跑腿项目总数
        int errandItemCount = managerService.queryErrandItemCount();
        HashMap<String, Integer> errandMap = managerService.queryErrandDisRepCount();
        // 查询学习项目、评论、回复总数
        HashMap<String, Integer> studyMap = managerService.queryStudyItemDisRepCount();
        // 查询失物招领
        HashMap<String, Integer> lostMap = managerService.queryLostItemDisRepCount();
        // 查询二手交易
        HashMap<String, Integer> secondMap = managerService.querySecondItemDisRepCount();
        // 查询校友圈
        HashMap<String, Integer> alumniMap = managerService.queryAlumni();
        // 计算项目总数
        int itemCount = errandItemCount + studyMap.get("itemCount") + lostMap.get("itemCount") + secondMap.get("itemCount");
        // 计算评论总数
        int disCount = errandMap.get("discussCount") + errandMap.get("replyCount") +
                        studyMap.get("discussCount") + studyMap.get("replyCount") +
                        lostMap.get("discussCount") + lostMap.get("replyCount") +
                        alumniMap.get("discussCount") + alumniMap.get("replyCount");
        HashMap<String, Integer> res = new HashMap<>();
        res.put("itemCount", itemCount);
        res.put("disCount", disCount);
        return IndexVO.SUCCESS(res);
    }
}
