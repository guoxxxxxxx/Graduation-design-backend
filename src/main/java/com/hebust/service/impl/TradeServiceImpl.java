package com.hebust.service.impl;

import com.github.pagehelper.PageHelper;
import com.hebust.config.ParamsConfig;
import com.hebust.entity.QueryCondition;
import com.hebust.entity.study.StudyDiscuss;
import com.hebust.entity.study.StudyReply;
import com.hebust.entity.trade.Trade;
import com.hebust.entity.trade.TradeDiscuss;
import com.hebust.entity.trade.TradeImg;
import com.hebust.entity.trade.TradeReply;
import com.hebust.mapper.TradeMapper;
import com.hebust.mapper.relation.TradeDiscussMapper;
import com.hebust.mapper.relation.TradeImgMapper;
import com.hebust.mapper.relation.TradeReplyMapper;
import com.hebust.service.TradeService;
import com.hebust.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    TradeImgMapper imgMapper;
    @Autowired
    TradeMapper tradeMapper;
    @Autowired
    TradeDiscussMapper discussMapper;
    @Autowired
    TradeReplyMapper replyMapper;

    @Override
    public int uploadImg(TradeImg img) {
        if (!img.getImgSrc().contains(ParamsConfig.IMG_UPLOAD_PATH)){
            img.setImgSrc(ParamsConfig.IMG_UPLOAD_PATH + img.getImgSrc());
        }
        return imgMapper.uploadImg(img);
    }

    @Override
    public int addNewItem(Trade trade) {
        return tradeMapper.addNewItem(trade);
    }


    @Override
    public List<Trade> queryItemsByCondition(QueryCondition condition) {
        PageHelper.startPage(condition.getPage(), ParamsConfig.PAGE_ITEMS_SIZE);
        List<Trade> trades = tradeMapper.queryByCondition(condition);
        // 将详细信息缩短到45字
        for (Trade trade : trades) {
            if (trade.getDetails().length() > 45){
                trade.setDetails(trade.getDetails().substring(0, 45) + " ...");
            }
        }
        return trades;
    }

    @Override
    public Trade queryDetailsByTid(int tid) {
        Trade trade = tradeMapper.queryDetailsByTid(tid);
        // 对trade中的图片进行处理 添加路径前缀
        List<String> new_imgUrls = new ArrayList<>();
        for (String imgUrl : trade.getImgUrls()) {
            if (!imgUrl.contains(ParamsConfig.BASE_PATH)){
                new_imgUrls.add(ParamsConfig.BASE_PATH + imgUrl);
            }
            else {
                new_imgUrls.add(imgUrl);
            }
        }
        trade.setImgUrls(new_imgUrls);
        return trade;
    }

    @Override
    public int wantToBuy(int tid, int tuid) {
        return tradeMapper.wantToBuy(tid, tuid);
    }

    @Override
    public int queryItemsCountByCondition(QueryCondition condition) {
        // 对空值的属性添加默认值
        if (condition.getFuzzyParam() == null){
            condition.setFuzzyParam("");
        }
        return tradeMapper.queryItemsCountByCondition(condition);
    }

    @Override
    public int updateByTid(Trade trade) {
        trade.setPubdate(DateUtils.getCurrentDateTimeString());
        return tradeMapper.updateByTid(trade);
    }

    @Override
    public List<String> queryImgByTid(int tid) {
        List<String> list = imgMapper.queryImg(tid);
        List<String> new_list = new ArrayList<>();
        // 补全图片路径
        for (String s : list) {
            if (!s.contains(ParamsConfig.BASE_PATH)){
                new_list.add(ParamsConfig.BASE_PATH + s);
            }
        }
        return new_list;
    }

    @Override
    public int fakeDeleteImg(String img) {
        return imgMapper.fakeDeleteImg(img);
    }

    @Override
    public List<TradeDiscuss> queryDiscuss(int tid, int page) {
        PageHelper.startPage(page, ParamsConfig.PAGE_DISCUSS_SIZE);
        List<TradeDiscuss> tradeDiscusses = discussMapper.queryDiscussByTid(tid);
        // 将所有用户的头像信息补全
        if (tradeDiscusses != null) {
            for (TradeDiscuss discuss : tradeDiscusses) {
                if (discuss.getCommentUser() != null && !discuss.getCommentUser().getAvatar().contains(ParamsConfig.BASE_PATH)) {
                    discuss.getCommentUser().setAvatar(ParamsConfig.BASE_PATH + discuss.getCommentUser().getAvatar());
                }
                if (discuss.getChildrenList() != null) {
                    for (TradeReply reply : discuss.getChildrenList()) {
                        if (reply.getCommentUser() != null && !reply.getCommentUser().getAvatar().contains(ParamsConfig.BASE_PATH)) {
                            reply.getCommentUser().setAvatar(ParamsConfig.BASE_PATH + reply.getCommentUser().getAvatar());
                        }
                        if (reply.getTargetUser() != null && !reply.getTargetUser().getAvatar().contains(ParamsConfig.BASE_PATH)) {
                            reply.getTargetUser().setAvatar(ParamsConfig.BASE_PATH + reply.getTargetUser().getAvatar());
                        }
                    }
                }
            }
        }
        return tradeDiscusses;
    }

    @Override
    public int sendDiscuss(TradeDiscuss discuss) {
        discuss.setCreateDate(DateUtils.getCurrentDateTimeString());
        return discussMapper.sendDiscuss(discuss);
    }

    @Override
    public int sendReply(TradeReply reply) {
        reply.setCreateDate(DateUtils.getCurrentDateTimeString());
        return replyMapper.doSendReply(reply);
    }

    @Override
    public int fakeDeleteItem(int tid) {
        // 删除回复信息
        replyMapper.fakeDeleteByItemId(tid);
        discussMapper.fakeDeleteDiscussItemId(tid);
        imgMapper.fakeDeleteImgByFatherId(tid);
        return tradeMapper.fakeDeleteItem(tid);
    }

    @Override
    public int queryDiscussCount(int tid) {
        return discussMapper.queryDiscussCount(tid);
    }
}
