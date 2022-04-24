package com.hebust.service;

import com.hebust.entity.table.DiscussTable;
import com.hebust.entity.table.ItemTable;
import com.hebust.entity.table.ReplyTable;

import java.util.HashMap;
import java.util.List;

public interface ManagerService {

    /**
     * 查询跑腿的项目数量
     */
    int queryErrandItemCount();

    /**
     * 查询跑腿的评论和回复数量
     */
    HashMap<String, Integer> queryErrandDisRepCount();

    /**
     * 查询跑腿订单的已完成，已接单，总订单信息
     */
    HashMap<String, Integer> queryErrandItemsStatusCount();

    /**
     * 查询所有跑腿项目
     */
    List<ItemTable> queryAllErrandItem();

    /**
     * 伪删除跑腿订单及其对应的评论，回复，图片
     */
    int fakeDeleteErrandItem(int eid);

    /**
     * 查询跑腿订单的评论信息 管理员端
     */
    List<DiscussTable> queryErrandDiscuss();

    /**
     * 伪删除跑腿订单的评论信息及与之对应的回复信息
     */
    int fakeDeleteErrandDiscuss(int id);

    /**
     * 查询回复信息
     */
    List<ReplyTable> queryErrandReply();

    /**
     * 伪删除回复信息
     */
    int fakeDeleteErrandReply(int id);

    // ===========================================学习模块========================================================

    /**
     * 查询项目的数量和评论数量
     */
    HashMap<String, Integer> queryStudyItemDisRepCount();

    /**
     * 查询数据总览界面
     */
    HashMap<String, Integer> queryStudyItemsStatusCount();

    /**
     * 查询所有项目
     */
    List<ItemTable> queryAllStudyItem();

    /**
     * 通过sid伪删除订单信息 和其关联的信息
     */
    int fakeDeleteStudyItem(int sid);

    /**
     * 查询评论信息
     */
    List<DiscussTable> queryStudyDiscuss();

    /**
     * 通过id伪删除 评论信息 和其子评论信息
     */
    int fakeDeleteStudyDiscussById(int id);

    /**
     * 查询回复信息
     */
    List<ReplyTable> queryStudyReply();

    /**
     * 伪删除回复信息
     */
    int fakeDeleteStudyReply(int id);

    // ==================================================二手交易区=======================================================

    /**
     * 查询项目的数量和评论数量
     */
    HashMap<String, Integer> querySecondItemDisRepCount();

    /**
     * 查询数据总览界面
     */
    HashMap<String, Integer> querySecondItemsStatusCount();

    /**
     * 查询所有项目
     */
    List<ItemTable> queryAllSecondItem();

    /**
     * 通过sid伪删除订单信息 和其关联的信息
     */
    int fakeDeleteSecondItem(int tid);

    /**
     * 查询评论信息
     */
    List<DiscussTable> querySecondDiscuss();

    /**
     * 通过id伪删除 评论信息 和其子评论信息
     */
    int fakeDeleteSecondDiscussById(int id);

    /**
     * 查询回复信息
     */
    List<ReplyTable> querySecondReply();

    /**
     * 伪删除回复信息
     */
    int fakeDeleteSecondReply(int id);

    // ====================================================失物招领======================================================

    /**
     * 查询项目的数量和评论数量
     */
    HashMap<String, Integer> queryLostItemDisRepCount();

    /**
     * 查询数据总览界面
     */
    HashMap<String, Integer> queryLostItemsStatusCount();

    /**
     * 查询所有项目
     */
    List<ItemTable> queryAllLostItem();

    /**
     * 通过sid伪删除订单信息 和其关联的信息
     */
    int fakeDeleteLostItem(int sid);

    /**
     * 查询评论信息
     */
    List<DiscussTable> queryLostDiscuss();

    /**
     * 通过id伪删除 评论信息 和其子评论信息
     */
    int fakeDeleteLostDiscussById(int id);

    /**
     * 查询回复信息
     */
    List<ReplyTable> queryLostReply();

    /**
     * 伪删除回复信息
     */
    int fakeDeleteLostReply(int id);


    // ==================================校友圈==========================================================================
    /**
     * 查询评论信息
     */
    List<DiscussTable> queryAlumniDiscuss();

    /**
     * 通过id伪删除 评论信息 和其子评论信息
     */
    int fakeDeleteAlumniDiscussById(int id);

    /**
     * 查询回复信息
     */
    List<ReplyTable> queryAlumniReply();

    /**
     * 伪删除回复信息
     */
    int fakeDeleteAlumniReply(int id);

}
