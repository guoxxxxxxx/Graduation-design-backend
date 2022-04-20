package com.hebust.controller;

import com.hebust.entity.QueryCondition;
import com.hebust.entity.trade.*;
import com.hebust.service.TradeService;
import com.hebust.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/trade")
@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    /**
     * 上传图片到服务器
     */
    @RequestMapping("/uploadImg")
    public TradeVO uploadImg(@RequestBody TradeImg img){
        int i = tradeService.uploadImg(img);
        if (i == 1){
            return TradeVO.SUCCESS;
        }
        else {
            return TradeVO.FAIL;
        }
    }

    /**
     * 向交易表中添加新项目以及上传图片
     */
    @RequestMapping("/addNewItem")
    public TradeVO addNewItem(@RequestBody Trade trade){
        trade.setPubdate(DateUtils.getCurrentDateTimeString());
        int i = tradeService.addNewItem(trade);
        TradeImg tradeImg = new TradeImg();
        tradeImg.setTid(trade.getTid());
        for (String imgUrl : trade.getImgUrls()) {
            tradeImg.setImgSrc(imgUrl);
            tradeService.uploadImg(tradeImg);
        }
        if (i == 1){
            return TradeVO.SUCCESS(trade.getTid());
        }
        else {
            return TradeVO.FAIL;
        }
    }

    /**
     * 条件查询信息
     */
    @RequestMapping("/queryItemsByCondition")
    public TradeVO queryItemsByCondition(@RequestBody QueryCondition condition){
        List<Trade> trades = tradeService.queryItemsByCondition(condition);
        return TradeVO.SUCCESS(trades);
    }

    /**
     * 根据tid查询订单详细信息
     */
    @RequestMapping("/queryDetailsByTid")
    public TradeVO queryDetailsByTid(@RequestParam int tid){
        Trade trade = tradeService.queryDetailsByTid(tid);
        return TradeVO.SUCCESS(trade);
    }

    /**
     * 预定订单
     */
    @RequestMapping("wantToBuy")
    public TradeVO wantToBuy(@RequestParam int tid, @RequestParam int tuid){
        int i = tradeService.wantToBuy(tid, tuid);
        return TradeVO.SUCCESS;
    }


    /**
     * 查询符合查询条件的订单的全部数量
     */
    @RequestMapping("queryItemsCountByCondition")
    public TradeVO queryItemsCountByCondition(@RequestBody QueryCondition condition){
        int i = tradeService.queryItemsCountByCondition(condition);
        return TradeVO.SUCCESS(i);
    }

    /**
     * 更新项目信息
     */
    @RequestMapping("updateByTid")
    public TradeVO updateByTid(@RequestBody Trade trade){
        int i = tradeService.updateByTid(trade);
        TradeImg tradeImg = new TradeImg();
        tradeImg.setTid(trade.getTid());
        for (String imgUrl : trade.getImgUrls()) {
            tradeImg.setImgSrc(imgUrl);
            tradeService.uploadImg(tradeImg);
        }
        if (i == 1){
            return TradeVO.SUCCESS;
        }
        else {
            return TradeVO.FAIL;
        }
    }

    /**
     * 通过tid查询与之对应的图片信息
     */
    @RequestMapping("/queryImgByTid")
    public TradeVO queryImgByTid(@RequestParam int tid){
        List<String> list = tradeService.queryImgByTid(tid);
        return TradeVO.SUCCESS(list);
    }

    /**
     * 根据图片名称伪删除图片
     */
    @RequestMapping("/fakeDeleteImg")
    public TradeVO fakeDeleteImg(@RequestParam String img){
        int i = tradeService.fakeDeleteImg(img);
        if (i == 1){
            return TradeVO.SUCCESS;
        }
        else {
            return TradeVO.FAIL;
        }
    }

    /**
     * 通过tid查询评论信息
     */
    @RequestMapping("/queryDiscussByTid")
    public TradeVO queryDiscussByTid(@RequestParam int tid, @RequestParam int page){
        List<TradeDiscuss> tradeDiscusses = tradeService.queryDiscuss(tid, page);
        return TradeVO.SUCCESS(tradeDiscusses);
    }

    /**
     * 发送评论信息
     */
    @RequestMapping("/sendDiscuss")
    public TradeVO sendDiscuss(@RequestBody TradeDiscuss discuss){
        int i = tradeService.sendDiscuss(discuss);
        if (i == 1){
            return TradeVO.SUCCESS;
        }
        else {
            return TradeVO.FAIL;
        }
    }

    /**
     * 发送回复信息
     */
    @RequestMapping("/sendReply")
    public TradeVO sendReply(@RequestBody TradeReply reply){
        int i = tradeService.sendReply(reply);
        if (i == 1){
            return TradeVO.SUCCESS;
        }
        else {
            return TradeVO.FAIL;
        }
    }

    /**
     * 伪删除信息
     */
    @RequestMapping("/fakeDeleteItem")
    public TradeVO fakeDeleteItem(@RequestParam int tid){
        int i = tradeService.fakeDeleteItem(tid);
        if (i == 1){
            return TradeVO.SUCCESS;
        }
        else {
            return TradeVO.FAIL;
        }
    }
}
