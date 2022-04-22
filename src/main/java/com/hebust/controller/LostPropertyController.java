package com.hebust.controller;

import com.hebust.config.ParamsConfig;
import com.hebust.entity.QueryCondition;
import com.hebust.entity.lostProperty.*;
import com.hebust.service.LostPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lostProperty")
public class LostPropertyController {

    @Autowired
    private LostPropertyService service;

    /**
     * 发布信息
     */
    @RequestMapping("publishNewItem")
    public LostPropertyVO publishNewItem(@RequestBody LostProperty record){
        int i = service.publishNewItem(record);
        LostImg img = new LostImg();
        img.setLid(record.getLid());
        for (String imgUrl : record.getImgUrls()) {
            img.setImgSrc(ParamsConfig.IMG_UPLOAD_PATH + imgUrl);
            service.uploadImg(img);
        }
        if (i == 1){
            return LostPropertyVO.SUCCESS(record.getLid());
        }
        else {
            return LostPropertyVO.FAIL;
        }
    }

    /**
     * 上传图片到数据库中
     */
    @RequestMapping("/uploadImg")
    public LostPropertyVO uploadImg(@RequestBody LostImg img){
        int i = service.uploadImg(img);
        return LostPropertyVO.SUCCESS;
    }

    /**
     * 通过条件分页查询所需要的信息
     */
    @RequestMapping("/queryByCondition")
    public LostPropertyVO queryByCondition(@RequestBody QueryCondition condition){
        QueryCondition queryCondition = QueryCondition.checkCondition(condition);
        List<LostProperty> lostProperties = service.queryByCondition(queryCondition);
        return LostPropertyVO.SUCCESS(lostProperties);
    }

    /**
     * 通过条件查询获取符合条件的所有数量
     */
    @RequestMapping("/queryCountByCondition")
    public LostPropertyVO queryCountByCondition(@RequestBody QueryCondition condition){
        QueryCondition queryCondition = QueryCondition.checkCondition(condition);
        int i = service.queryCountByCondition(queryCondition);
        return LostPropertyVO.SUCCESS(i);
    }

    /**
     * 根据lid查询项目详细信息
     */
    @RequestMapping("/queryDetailsByLid")
    public LostPropertyVO queryDetailsByLid(@RequestParam int lid){
        LostProperty lostProperty = service.queryDetailsByLid(lid);
        return LostPropertyVO.SUCCESS(lostProperty);
    }

    /**
     * 通过lid将项目标记为已完成
     */
    @RequestMapping("/updateIsAchieveByLid")
    public LostPropertyVO updateIsAchieveByLid(@RequestParam int lid){
        int i = service.updateIsAchieveByLid(lid);
        if (i == 1){
            return LostPropertyVO.SUCCESS;
        }
        else {
            return LostPropertyVO.FAIL;
        }
    }

    /**
     * 删除信息
     */
    @RequestMapping("/fakeDeleteByLid")
    public LostPropertyVO fakeDeleteByLid(@RequestParam int lid){
        int i = service.fakeDeleteByLid(lid);
        if (i == 1){
            return LostPropertyVO.SUCCESS;
        }
        else {
            return LostPropertyVO.FAIL;
        }
    }

    /**
     * 通过lid查询与之对应的图片信息
     */
    @RequestMapping("/queryImgByLid")
    public LostPropertyVO queryImgByLid(@RequestParam int lid){
        List<String> list = service.queryImgByLid(lid);
        return LostPropertyVO.SUCCESS(list);
    }

    /**
     * 伪删除待删除的图片
     */
    @RequestMapping("/fakeDeleteImg")
    public LostPropertyVO fakeDeleteImg(@RequestParam String imgSrc){
        int i = service.fakeDeleteImg(imgSrc);
        return LostPropertyVO.SUCCESS;
    }

    /**
     * 更新项目信息
     */
    @RequestMapping("/updateByLid")
    public LostPropertyVO updateByLid(@RequestBody LostProperty property){
        int i = service.updateByLid(property);
        return LostPropertyVO.SUCCESS;
    }

    /**
     * 查询当前页面所有的评论数量
     */
    @RequestMapping("/queryDiscussCountByLid")
    public LostPropertyVO queryDiscussCountByLid(@RequestParam int lid){
        int i = service.queryDiscussCountByLid(lid);
        return LostPropertyVO.SUCCESS(i);
    }

    /**
     * 发送评论
     */
    @RequestMapping("/sendDiscuss")
    public LostPropertyVO sendDiscuss(@RequestBody LostDiscuss discuss){
        int i = service.sendDiscuss(discuss);
        return LostPropertyVO.SUCCESS;
    }

    /**
     * 发送回复
     */
    @RequestMapping("/doSendReply")
    public LostPropertyVO doSendReply(@RequestBody LostReply reply){
        service.doSendReply(reply);
        return LostPropertyVO.SUCCESS;
    }

    /**
     * 查询当前界面的评论及其子评论 分页查询
     */
    @RequestMapping("/queryDiscussByLid")
    public LostPropertyVO queryDiscussByLid(@RequestParam int lid, @RequestParam(defaultValue = "1")int page){
        List<LostDiscuss> lostDiscusses = service.queryDiscussById(lid, page);
        return LostPropertyVO.SUCCESS(lostDiscusses);
    }
}
