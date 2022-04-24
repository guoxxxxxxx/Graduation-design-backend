package com.hebust.service.impl;

import com.hebust.entity.table.DiscussTable;
import com.hebust.entity.table.ItemTable;
import com.hebust.entity.table.ReplyTable;
import com.hebust.mapper.ErrandMapper;
import com.hebust.mapper.LostPropertyMapper;
import com.hebust.mapper.StudyMapper;
import com.hebust.mapper.TradeMapper;
import com.hebust.mapper.relation.*;
import com.hebust.service.ManagerService;
import com.hebust.utils.ShortContentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    // 跑腿bean注入
    @Autowired
    ErrandMapper errandMapper;
    @Autowired
    ErrandDiscussMapper errandDiscussMapper;
    @Autowired
    ErrandReplyMapper errandReplyMapper;
    @Autowired
    ErrandImgMapper errandImgMapper;

    @Override
    public int queryErrandItemCount() {
        return errandMapper.queryCount();
    }

    @Override
    public HashMap<String, Integer> queryErrandDisRepCount() {
        int discussAllCount = errandDiscussMapper.queryDiscussAllCount();
        int replyCount = errandReplyMapper.queryReplyCount();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("discussCount", discussAllCount);
        map.put("replyCount", replyCount);
        return map;
    }

    @Override
    public HashMap<String, Integer> queryErrandItemsStatusCount() {
        int all = errandMapper.queryCount();
        int achieve = errandMapper.queryAchieveCount();
        int take = errandMapper.queryTakeOrdersCount();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("all", all);
        map.put("achieve", achieve);
        map.put("take", take);
        return map;
    }

    @Override
    public List<ItemTable> queryAllErrandItem() {
        List<ItemTable> errandTables = errandMapper.managerQueryAll();
        for (int i = 0; i < errandTables.size(); i++) {
            if (errandTables.get(i).getIsAchieve() == 1){
                errandTables.get(i).setStatus("已完成");
            }
            else if (errandTables.get(i).getTakeOrdersUser() != null){
                errandTables.get(i).setStatus("已被接单");
            }
            else {
                errandTables.get(i).setStatus("未完成");
                errandTables.get(i).setTakeOrdersUser("无");
            }
            // 精简标题
            errandTables.get(i).setTitle(ShortContentUtils.toShortContent(errandTables.get(i).getTitle()));
        }
        return errandTables;
    }

    @Override
    public int fakeDeleteErrandItem(int eid) {
        // 删除回复信息
        int i = errandReplyMapper.fakeDeleteByEid(eid);
        // 删除评论信息
        int i1 = errandDiscussMapper.fakeDeleteDiscussByEid(eid);
        // 删除图片信息
        int i2 = errandImgMapper.fakeDeleteImgByEid(eid);
        // 删除回复信息
        int i3 = errandMapper.fakeDeleteItem(eid);
        return i + i2 + i1 + i3;
    }

    @Override
    public List<DiscussTable> queryErrandDiscuss() {
        List<DiscussTable> discussTables = errandDiscussMapper.queryDiscussTable();
        return ShortContentUtils.discussTableToShort(discussTables);
    }

    @Override
    public int fakeDeleteErrandDiscuss(int id) {
        int i = errandReplyMapper.fakeDeleteByParentId(id);
        int i1 = errandDiscussMapper.fakeDeleteDiscussById(id);
        return i+i1;
    }

    @Override
    public List<ReplyTable> queryErrandReply() {
        List<ReplyTable> replyTables = errandReplyMapper.queryReplyManager();
        return ShortContentUtils.replyTableToShort(replyTables);
    }

    @Override
    public int fakeDeleteErrandReply(int id) {
        return errandReplyMapper.fakeDeleteById(id);
    }

    @Autowired
    private StudyMapper studyMapper;
    @Autowired
    private StudyDiscussMapper studyDiscussMapper;
    @Autowired
    private StudyReplyMapper studyReplyMapper;
    @Autowired
    private StudyImgMapper studyImgMapper;

    @Override
    public HashMap<String, Integer> queryStudyItemDisRepCount() {
        HashMap<String, Integer> map = new HashMap<>();
        // 查询项目总数量
        int itemCount = studyMapper.queryCount();
        map.put("itemCount", itemCount);
        // 查询评论总数量
        int discussCount = studyDiscussMapper.queryDiscussCount();
        map.put("discussCount", discussCount);
        // 查询回复总数量
        int replyCount = studyReplyMapper.queryReplyCount();
        map.put("replyCount", replyCount);
        return map;
    }

    @Override
    public HashMap<String, Integer> queryStudyItemsStatusCount() {
        int all = studyMapper.queryCount();
        int achieve = studyMapper.queryAchieveCount();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("all", all);
        map.put("achieve", achieve);
        return map;
    }

    @Override
    public List<ItemTable> queryAllStudyItem() {
        List<ItemTable> itemTables = studyMapper.queryAllItemManager();
        for (ItemTable itemTable : itemTables) {
            if (itemTable.getIsAchieve() == 1){
                itemTable.setStatus("已完成");
            }
            else {
                itemTable.setStatus("未完成");
            }
            itemTable.setTitle(ShortContentUtils.toShortContent(itemTable.getTitle()));
        }
        return itemTables;
    }

    @Override
    public int fakeDeleteStudyItem(int sid) {
        // 删除回复信息
        int i = studyReplyMapper.fakeDeleteReplyBySid(sid);
        // 删除评论信息
        int j = studyDiscussMapper.fakeDeleteDiscussBySid(sid);
        // 删除图片信息
        int k = studyImgMapper.fakeDeleteImgBySid(sid);
        // 删除项目信息
        int w = studyMapper.fakeDeleteBySid(sid);
        return i + j + k + w;
    }

    @Override
    public List<DiscussTable> queryStudyDiscuss() {
        return ShortContentUtils.discussTableToShort(studyDiscussMapper.queryDiscussTable());
    }

    @Override
    public int fakeDeleteStudyDiscussById(int id) {
        studyReplyMapper.fakeDeleteReplyByFatherId(id);
        return studyDiscussMapper.fakeDeleteDiscuss(id);
    }

    @Override
    public List<ReplyTable> queryStudyReply() {
        List<ReplyTable> replyTables = studyReplyMapper.queryReply();
        return ShortContentUtils.replyTableToShort(replyTables);
    }

    @Override
    public int fakeDeleteStudyReply(int id) {
        return studyReplyMapper.fakeDeleteRelpy(id);
    }


    @Autowired
    TradeMapper tradeMapper;
    @Autowired
    TradeDiscussMapper tradeDiscussMapper;
    @Autowired
    TradeReplyMapper tradeReplyMapper;
    @Autowired
    TradeImgMapper tradeImgMapper;

    @Override
    public HashMap<String, Integer> querySecondItemDisRepCount() {
        int itemCount = tradeMapper.queryCount();
        int discussCount = tradeDiscussMapper.queryDiscussCountManager();
        int replyCount = tradeReplyMapper.queryReplyCountManager();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("itemCount", itemCount);
        map.put("discussCount", discussCount);
        map.put("replyCount", replyCount);
        return map;
    }

    @Override
    public HashMap<String, Integer> querySecondItemsStatusCount() {
        int all = tradeMapper.queryCount();
        int take = tradeMapper.queryTakeOrdersCount();
        int achieve = tradeMapper.queryAchieveCount();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("all", all);
        map.put("take", take);
        map.put("achieve", achieve);
        return map;
    }

    @Override
    public List<ItemTable> queryAllSecondItem() {
        List<ItemTable> itemTables = tradeMapper.queryTradeItems();
        for (ItemTable itemTable : itemTables) {
            if (itemTable.getIsAchieve() == 1){
                itemTable.setStatus("已完成");
            }
            else if (itemTable.getTakeOrdersUser() != null){
                itemTable.setStatus("已接单");
            }
            else {
                itemTable.setStatus("未完成");
            }
            itemTable.setTitle(ShortContentUtils.toShortContent(itemTable.getTitle()));
        }
        return itemTables;
    }

    @Override
    public int fakeDeleteSecondItem(int tid) {
        // 删除回复
        int i = tradeReplyMapper.fakeDeleteByItemId(tid);
        // 删除评论
        int j = tradeDiscussMapper.fakeDeleteDiscussItemId(tid);
        // 删除图片
        int k = tradeImgMapper.fakeDeleteImgByFatherId(tid);
        // 删除项目本体
        int w = tradeMapper.fakeDeleteItem(tid);
        return i + j + k + w;
    }

    @Override
    public List<DiscussTable> querySecondDiscuss() {
        List<DiscussTable> discussTables = tradeDiscussMapper.queryDiscussItemManager();
        List<DiscussTable> discussTables1 = ShortContentUtils.discussTableToShort(discussTables);
        return discussTables1;
    }

    @Override
    public int fakeDeleteSecondDiscussById(int id) {
        int i = tradeDiscussMapper.fakeDeleteById(id);
        int j = tradeReplyMapper.fakeDeleteByFatherId(id);
        return i + j;
    }

    @Override
    public List<ReplyTable> querySecondReply() {
        List<ReplyTable> replyTables = tradeReplyMapper.queryReplyTable();
        List<ReplyTable> replyTables1 = ShortContentUtils.replyTableToShort(replyTables);
        return replyTables1;
    }

    @Override
    public int fakeDeleteSecondReply(int id) {
        return tradeReplyMapper.fakeDeleteById(id);
    }

    @Autowired
    LostPropertyMapper lostPropertyMapper;
    @Autowired
    LostDiscussMapper lostDiscussMapper;
    @Autowired
    LostReplyMapper lostReplyMapper;
    @Autowired
    LostImgMapper lostImgMapper;

    @Override
    public HashMap<String, Integer> queryLostItemDisRepCount() {
        return null;
    }

    @Override
    public HashMap<String, Integer> queryLostItemsStatusCount() {
        int all = lostPropertyMapper.queryCount();
        int achieve = lostPropertyMapper.queryAchieveCount();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("all", all);
        map.put("achieve", achieve);
        return map;
    }

    @Override
    public List<ItemTable> queryAllLostItem() {
        return null;
    }

    @Override
    public int fakeDeleteLostItem(int sid) {
        return 0;
    }

    @Override
    public List<DiscussTable> queryLostDiscuss() {
        return null;
    }

    @Override
    public int fakeDeleteLostDiscussById(int id) {
        return 0;
    }

    @Override
    public List<ReplyTable> queryLostReply() {
        return null;
    }

    @Override
    public int fakeDeleteLostReply(int id) {
        return 0;
    }
}
