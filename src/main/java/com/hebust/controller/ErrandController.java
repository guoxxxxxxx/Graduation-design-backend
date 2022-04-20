package com.hebust.controller;

import com.hebust.config.ParamsConfig;
import com.hebust.entity.QueryCondition;
import com.hebust.entity.errand.Errand;
import com.hebust.entity.errand.ErrandDiscuss;
import com.hebust.entity.errand.ErrandReply;
import com.hebust.entity.errand.ErrandVO;
import com.hebust.service.ErrandService;
import com.hebust.service.relation.ErrandDiscussService;
import com.hebust.service.relation.ErrandImgService;
import com.hebust.utils.DateUtils;
import com.hebust.utils.SimplifyDetailsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/errand")
public class ErrandController {

    @Autowired
    private ErrandService errandService;
    @Autowired
    private ErrandImgService errandImgService;
    @Autowired
    private ErrandDiscussService errandDiscussService;

    /**
     * 查询所有跑腿订单
     */
    @RequestMapping("/queryAll")
    public ErrandVO queryAll(){
        List<Errand> errands = errandService.selectAll();
        // 精简一下详细信息，设置为 35 个字符 + ... 的格式
        for (Errand errand : errands) {
           errand.setDetails(SimplifyDetailsUtils.simplifyDetails(errand.getDetails()));
        }
        return new ErrandVO(200, "success", errands);
    }

    /**
     * 条件查询订单信息
     */
    @RequestMapping("/queryByCondition")
    public ErrandVO queryByCondition(@RequestBody QueryCondition condition){
        if (condition.getPage() <= 0){
            condition.setPage(1);
        }
        if (condition.getFuzzyParam() == null){
            condition.setFuzzyParam("");
        }
        List<Errand> errands = errandService.queryByCondition(condition);
        return new ErrandVO(200, "success", errands);
    }

    /**
     * 根据eid查询订单详细信息
     */
    @RequestMapping("/queryDetailsByEid")
    public ErrandVO queryDetailsByEid(@RequestParam int eid){
        Errand errand = errandService.queryDetailsByEid(eid);
        List<String> newImgList = new ArrayList<>();
        for (String imgUrl : errand.getImgUrls()) {
            newImgList.add(ParamsConfig.BASE_PATH + imgUrl);
        }
        errand.setImgUrls(newImgList);
        return new ErrandVO(200, "success", errand);
    }

    /**
     * 根据分类查询跑腿订单项目
     * 2022-04-19 添加分页查询约束
     */
    @RequestMapping("/queryItemByCategory")
    public ErrandVO queryItemByCategory(@RequestParam String category){
        List<Errand> errands = errandService.queryItemByCategory(category);
        for (Errand errand : errands) {
            errand.setDetails(SimplifyDetailsUtils.simplifyDetails(errand.getDetails()));
        }
        return new ErrandVO(200, "success", errands);
    }

    /**
     * 添加新的跑腿订单
     * 并上传图片
     */
    @RequestMapping("addErrandItem")
    @Transactional
    public ErrandVO addErrandItem(@RequestBody Errand errand){
        if (errand == null){
            return new ErrandVO(400, "对象不存在", null);
        }
        else{
            // 将当前时间添加到对象中
            errand.setPubdate(DateUtils.getCurrentDateTimeString());
            // 向数据库中添加跑腿订单
            int i = errandService.addErrandItem(errand);
            // 将图片插入到数据库中
            int count = errandImgService.insertErrandImg(errand.getEid(), errand.getImgUrls());

            if (i == 1 && count == errand.getImgUrls().size()){
                return new ErrandVO(200, "success", errand.getEid());
            }
            else {
                return new ErrandVO(400, "fail", null);
            }
        }
    }

    /**
     * 更新接单用户id
     */
    @RequestMapping("/updateEUid")
    public ErrandVO updateUid(@RequestBody Errand errand){
        if (errand == null){
            return new ErrandVO(400, "fail", null);
        }
        else {
            int i = errandService.updateTakeOrdersUid(errand);
            if (i == 1){
                return new ErrandVO(200, "success", null);
            }
            else {
                return ErrandVO.FAIL;
            }
        }
    }

    /**
     * 伪删除跑腿订单信息及其对应的图片
     */
    @RequestMapping("/fakeDeleteItemByEid")
    @Transactional
    public ErrandVO fakeDeleteItemByEid(@RequestBody Errand errand){
        if (errand == null){
            return ErrandVO.FAIL;
        }
        else {
            // 伪删除订单信息
            int i = errandService.fakeDeleteItemByEid(errand.getEid());
            // 伪删除订单对应的图片信息
            errandImgService.fakeDeleteImgByEid(errand.getEid());
            if (i == 1){
                return ErrandVO.SUCCESS;
            }
            else {
                return ErrandVO.FAIL;
            }
        }
    }

    /**
     * 根据eid更新跑腿订单信息
     */
    @RequestMapping("/updateErrandItemByEid")
    public ErrandVO updateErrandItem(@RequestBody Errand errand){
        if (errand == null){
            return ErrandVO.FAIL;
        }
        else{
            int i = errandService.updateErrandItemByEid(errand);
            // 将图片插入到数据库中
            int count = errandImgService.insertErrandImg(errand.getEid(), errand.getImgUrls());
            if (i == 1){
                return ErrandVO.SUCCESS;
            }
            else {
                return ErrandVO.FAIL;
            }
        }
    }

    /**
     * 根据src_img伪删除图片
     */
    @RequestMapping("fakeDeleteImgByImgSrc")
    public ErrandVO fakeDeleteImgByImgSrc(@RequestParam String img_src){
        if (img_src == null){
            return ErrandVO.FAIL;
        }else {
            int i = errandImgService.fakeDeleteImgByImgSrc(img_src);
            if (i == 1){
                return ErrandVO.SUCCESS;
            }
            else {
                return ErrandVO.FAIL;
            }
        }
    }

    /**
     * 根据eid设置订单完成状态
     */
    @RequestMapping("updateErrandIsAchieveStateByEid")
    public ErrandVO updateErrandIsAchieveStateByEid(@RequestParam int eid){
        if (eid == 0){
            return ErrandVO.FAIL;
        }
        else{
            int i = errandService.updateErrandIsAchieveStateByEid(eid);
            if (i == 1){
                return ErrandVO.SUCCESS;
            }
            else {
                return ErrandVO.FAIL;
            }
        }
    }

    /**
     * 通过eid查询订单所有评论及其子评论
     * 2022-04-19 添加分页查询约束
     */
    @RequestMapping("queryAllCommentsAndChildComments")
    public ErrandVO queryAllCommentsAndChildComments(@RequestParam int eid, @RequestParam(defaultValue = "1")int page){
        if (eid == 0){
            return ErrandVO.FAIL;
        }
        else {
            List<ErrandDiscuss> errandDiscusses = errandDiscussService.selectAllDiscussByEid(eid, page);
            // 补全路径地址
            if (errandDiscusses != null) {
                for (ErrandDiscuss discuss : errandDiscusses) {
                    if (discuss.getCommentUser() != null && !discuss.getCommentUser().getAvatar().contains(ParamsConfig.BASE_PATH)) {
                        discuss.getCommentUser().setAvatar(ParamsConfig.BASE_PATH + discuss.getCommentUser().getAvatar());
                    }
                    if (discuss.getTargetUser() != null && !discuss.getTargetUser().getAvatar().contains(ParamsConfig.BASE_PATH)) {
                        discuss.getTargetUser().setAvatar(ParamsConfig.BASE_PATH + discuss.getTargetUser().getAvatar());
                    }
                    if (discuss.getChildrenList() != null) {
                        for (ErrandReply reply : discuss.getChildrenList()) {
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
            return new ErrandVO(200, "success", errandDiscusses);
        }
    }

    /**
     * 发表评论
     */
    @RequestMapping("/sendDiscuss")
    public ErrandVO sendDiscuss(@RequestBody ErrandDiscuss errandDiscuss){
        if (errandDiscuss == null || errandDiscuss.getContent().length() == 0){
            return ErrandVO.FAIL;
        }
        else {
            errandDiscuss.setCreateDate(DateUtils.getCurrentDateTimeString());
            int i = errandDiscussService.insertDiscuss(errandDiscuss);
            if (i == 1){
                return ErrandVO.SUCCESS;
            }
            else {
                return ErrandVO.FAIL;
            }
        }
    }

    /**
     * 发表回复
     */
    @RequestMapping("/sendReply")
    public ErrandVO sendReply(@RequestBody ErrandReply reply){
        if (reply == null || reply.getContent() == null || reply.getContent().length() == 0){
            return ErrandVO.FAIL;
        }
        else {
            reply.setCreateDate(DateUtils.getCurrentDateTimeString());
            int i = errandDiscussService.insertReply(reply);
            if (i == 1){
                return ErrandVO.SUCCESS;
            }
            else {
                return ErrandVO.FAIL;
            }
        }
    }

    /**
     * 查询符合条件的项目的数量
     */
    @RequestMapping("/queryCountByCondition")
    public ErrandVO queryCountByCondition(@RequestBody QueryCondition condition){
        int i = errandService.queryCountByCondition(condition);
        return new ErrandVO(200, "success", i);
    }

    /**
     * 查询跑腿评论信息的数量
     */
    @RequestMapping("/queryDiscussCount")
    public ErrandVO queryDiscussCount(@RequestParam int eid){
        int i = errandService.queryDiscussCount(eid);
        return new ErrandVO(200, "success", i);
    }
}
