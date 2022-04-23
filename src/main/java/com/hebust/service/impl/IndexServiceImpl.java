package com.hebust.service.impl;

import com.hebust.entity.other.IndexVO;
import com.hebust.entity.other.Percentage;
import com.hebust.mapper.ErrandMapper;
import com.hebust.mapper.LostPropertyMapper;
import com.hebust.mapper.StudyMapper;
import com.hebust.mapper.TradeMapper;
import com.hebust.mapper.relation.LostReplyMapper;
import com.hebust.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    ErrandMapper errandMapper;
    @Autowired
    StudyMapper studyMapper;
    @Autowired
    TradeMapper tradeMapper;
    @Autowired
    LostPropertyMapper lostPropertyMapper;

    @Override
    public Percentage queryPercentage() {
        int allCount = 0;
        int canTakeCount = 0;
        int takeCount = 0;
        int achieveCount = 0;
        Percentage percentage = new Percentage();

        // 查询跑腿订单的数量
        canTakeCount += errandMapper.queryCount();
        takeCount += errandMapper.queryTakeOrdersCount();
        achieveCount += errandMapper.queryAchieveCount();

        // 查询学习订单数量
        allCount += studyMapper.queryCount();
        achieveCount += studyMapper.queryAchieveCount();

        // 查询交易订单数量
        canTakeCount += tradeMapper.queryCount();
        takeCount += tradeMapper.queryTakeOrdersCount();
        achieveCount += tradeMapper.queryAchieveCount();

        // 查询失物招领
        allCount += lostPropertyMapper.queryCount();
        achieveCount += lostPropertyMapper.queryAchieveCount();

        // 保存

        allCount += canTakeCount;

        String dontTake, take, achieve;

        // 计算未被接单
        if (canTakeCount - takeCount == 0 || canTakeCount == 0){
            dontTake = "0";
        }else {
            if (((((double)canTakeCount - takeCount)*100)/canTakeCount + "").length()>5)
                dontTake = ((((double)canTakeCount - takeCount)*100)/canTakeCount + "").substring(0, 5);
            else {
                dontTake = ((((double)canTakeCount - takeCount)*100)/canTakeCount + "");
            }
        }
        // 计算已被接单
        if (takeCount == 0 || canTakeCount == 0){
            take = "0";
        } else {
            if ((((double)takeCount/canTakeCount)*100 + "").length() > 5){
                take = (((double)takeCount/canTakeCount)*100 + "").substring(0, 5);
            } else {
                take = (((double)takeCount/canTakeCount)*100 + "");
            }
        }
        // 计算已完成
        if ((double)achieveCount == 0 || allCount == 0){
            achieve = "0";
        }
        else {
            if ((((double)achieveCount/allCount)*100 + "").length() > 5){
                achieve = (((double)achieveCount/allCount)*100 + "").substring(0, 5);
            } else {
                achieve = (((double)achieveCount/allCount)*100 + "");
            }
        }
        percentage.setTake(take);
        percentage.setDont(dontTake);
        percentage.setAchieve(achieve);
        return percentage;
    }
}
