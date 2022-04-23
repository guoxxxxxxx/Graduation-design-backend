package com.hebust.service.impl;

import com.hebust.entity.item.ErrandTable;
import com.hebust.mapper.ErrandMapper;
import com.hebust.mapper.relation.ErrandDiscussMapper;
import com.hebust.mapper.relation.ErrandReplyMapper;
import com.hebust.service.ManagerService;
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
    public List<ErrandTable> queryAllErrandItem() {
        List<ErrandTable> errandTables = errandMapper.managerQueryAll();
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
        }
        return errandTables;
    }
}
