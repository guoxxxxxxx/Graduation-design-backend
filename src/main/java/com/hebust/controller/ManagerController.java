package com.hebust.controller;

import com.hebust.entity.table.DiscussTable;
import com.hebust.entity.table.ItemTable;
import com.hebust.entity.other.ManagerVO;
import com.hebust.entity.table.ReplyTable;
import com.hebust.service.ManagerService;
import com.hebust.utils.PreComputeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        List<ItemTable> errandTables = managerService.queryAllErrandItem();
        return ManagerVO.SUCCESS(errandTables);
    }

    /**
     * 伪删除跑腿订单
     */
    @RequestMapping("/errand/fakeDeleteItem")
    public ManagerVO fakeDeleteItem(@RequestParam int id) {
        int i = managerService.fakeDeleteErrandItem(id);
        return ManagerVO.SUCCESS(i);
    }

    /**
     * 查询跑腿订单评论信息
     */
    @RequestMapping("/errand/queryDiscuss")
    public ManagerVO queryErrandDiscuss(){
        List<DiscussTable> discussTables = managerService.queryErrandDiscuss();
        return ManagerVO.SUCCESS(discussTables);
    }

    /**
     * 删除跑腿订单回复信息
     */
    @RequestMapping("/errand/fakeDeleteDiscuss")
    public ManagerVO fakeDeleteErrandDiscuss(int id){
        int i = managerService.fakeDeleteErrandDiscuss(id);
        return ManagerVO.SUCCESS(i);
    }

    /**
     * 查询回复信息 管理员
     */
    @RequestMapping("/errand/queryReply")
    public ManagerVO queryReply(){
        List<ReplyTable> replyTables = managerService.queryErrandReply();
        return ManagerVO.SUCCESS(replyTables);
    }

    /**
     * 删除回复信息
     */
    @RequestMapping("/errand/fakeDeleteReply")
    public ManagerVO fakeDeleteErrandReply(@RequestParam int id){
        int i = managerService.fakeDeleteErrandReply(id);
        return ManagerVO.SUCCESS(i);
    }


    // ===============================================学习交流部分========================================================

    /**
     * 查询项目数量和评论数量
     */
    @RequestMapping("/study/queryItemDisCount")
    public ManagerVO queryStudyItemDisCount(){
        HashMap<String, Integer> map = managerService.queryStudyItemDisRepCount();
        return ManagerVO.SUCCESS(map);
    }

    /**
     * 查询数据总览界面数据
     */
    @RequestMapping("/study/queryPercentage")
    public ManagerVO queryStudyPercentage(){
        HashMap<String, Integer> map = managerService.queryStudyItemsStatusCount();
        HashMap<String, String> compute = PreComputeUtils.compute(map.get("all"), 0, 0, map.get("achieve"));
        return ManagerVO.SUCCESS(compute);
    }

    /**
     * 查询所有项目
     */
    @RequestMapping("/study/queryAllItem")
    public ManagerVO queryStudyAllItem(){
        List<ItemTable> itemTables = managerService.queryAllStudyItem();
        return ManagerVO.SUCCESS(itemTables);
    }

    /**
     * 伪删除订单
     */
    @RequestMapping("/study/fakeDeleteItem")
    public ManagerVO fakeDeleteStudyItem(@RequestParam int id) {
        int i = managerService.fakeDeleteStudyItem(id);
        return ManagerVO.SUCCESS(i);
    }

    /**
     * 查询订单评论信息
     */
    @RequestMapping("/study/queryDiscuss")
    public ManagerVO queryStudyDiscuss(){
        List<DiscussTable> discussTables = managerService.queryStudyDiscuss();
        return ManagerVO.SUCCESS(discussTables);
    }

    /**
     * 删除订单回复信息
     */
    @RequestMapping("/study/fakeDeleteDiscuss")
    public ManagerVO fakeDeleteStudyDiscuss(int id){
        int i = managerService.fakeDeleteStudyDiscussById(id);
        return ManagerVO.SUCCESS(i);
    }

    /**
     * 查询回复信息 管理员
     */
    @RequestMapping("/study/queryReply")
    public ManagerVO queryStudyReply(){
        List<ReplyTable> replyTables = managerService.queryStudyReply();
        return ManagerVO.SUCCESS(replyTables);
    }

    /**
     * 删除回复信息
     */
    @RequestMapping("/study/fakeDeleteReply")
    public ManagerVO fakeDeleteStudyReply(@RequestParam int id){
        int i = managerService.fakeDeleteStudyReply(id);
        return ManagerVO.SUCCESS(i);
    }


    // =================================================二手交易区========================================================

    /**
     * 查询项目数量和评论数量
     */
    @RequestMapping("/second/queryItemDisCount")
    public ManagerVO querySecondItemDisCount(){
        HashMap<String, Integer> map = managerService.querySecondItemDisRepCount();
        return ManagerVO.SUCCESS(map);
    }

    /**
     * 查询数据总览界面数据
     */
    @RequestMapping("/second/queryPercentage")
    public ManagerVO querySecondPercentage(){
        HashMap<String, Integer> map = managerService.querySecondItemsStatusCount();
        HashMap<String, String> compute = PreComputeUtils.compute(map.get("all"), map.get("all"), map.get("take"), map.get("achieve"));
        return ManagerVO.SUCCESS(compute);
    }

    /**
     * 查询所有项目
     */
    @RequestMapping("/second/queryAllItem")
    public ManagerVO querySecondAllItem(){
        List<ItemTable> itemTables = managerService.queryAllSecondItem();
        return ManagerVO.SUCCESS(itemTables);
    }

    /**
     * 伪删除订单
     */
    @RequestMapping("/second/fakeDeleteItem")
    public ManagerVO fakeDeleteSecondItem(@RequestParam int id) {
        int i = managerService.fakeDeleteSecondItem(id);
        return ManagerVO.SUCCESS(i);
    }

    /**
     * 查询订单评论信息
     */
    @RequestMapping("/second/queryDiscuss")
    public ManagerVO querySecondDiscuss(){
        List<DiscussTable> discussTables = managerService.querySecondDiscuss();
        return ManagerVO.SUCCESS(discussTables);
    }

    /**
     * 删除订单回复信息
     */
    @RequestMapping("/second/fakeDeleteDiscuss")
    public ManagerVO fakeDeleteSecondDiscuss(int id){
        int i = managerService.fakeDeleteSecondDiscussById(id);
        return ManagerVO.SUCCESS(i);
    }

    /**
     * 查询回复信息 管理员
     */
    @RequestMapping("/second/queryReply")
    public ManagerVO querySecondReply(){
        List<ReplyTable> replyTables = managerService.querySecondReply();
        return ManagerVO.SUCCESS(replyTables);
    }

    /**
     * 删除回复信息
     */
    @RequestMapping("/second/fakeDeleteReply")
    public ManagerVO fakeDeleteSecondReply(@RequestParam int id){
        int i = managerService.fakeDeleteSecondReply(id);
        return ManagerVO.SUCCESS(i);
    }


    // ===============================================失物招领区==========================================================
    /**
     * 查询项目数量和评论数量
     */
    @RequestMapping("/lost/queryItemDisCount")
    public ManagerVO queryLostItemDisCount(){
        return null;
    }

    /**
     * 查询数据总览界面数据
     */
    @RequestMapping("/lost/queryPercentage")
    public ManagerVO queryLostPercentage(){
        return null;
    }

    /**
     * 查询所有项目
     */
    @RequestMapping("/lost/queryAllItem")
    public ManagerVO queryLostAllItem(){
        return null;
    }

    /**
     * 伪删除订单
     */
    @RequestMapping("/lost/fakeDeleteItem")
    public ManagerVO fakeDeleteLostItem(@RequestParam int id) {
        return null;
    }

    /**
     * 查询订单评论信息
     */
    @RequestMapping("/lost/queryDiscuss")
    public ManagerVO queryLostDiscuss(){
        return null;
    }

    /**
     * 删除订单回复信息
     */
    @RequestMapping("/lost/fakeDeleteDiscuss")
    public ManagerVO fakeDeleteLostDiscuss(int id){
        return null;
    }

    /**
     * 查询回复信息 管理员
     */
    @RequestMapping("/lost/queryReply")
    public ManagerVO queryLostReply(){
        return null;
    }

    /**
     * 删除回复信息
     */
    @RequestMapping("/lost/fakeDeleteReply")
    public ManagerVO fakeDeleteLostReply(@RequestParam int id){
        return null;
    }

    // =================================================================================================================
}
