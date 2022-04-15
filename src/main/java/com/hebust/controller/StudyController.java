package com.hebust.controller;

import com.hebust.config.ParamsConfig;
import com.hebust.entity.errand.ErrandDiscuss;
import com.hebust.entity.errand.ErrandReply;
import com.hebust.entity.study.*;
import com.hebust.entity.user.User;
import com.hebust.service.StudyService;
import com.hebust.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/study")
public class StudyController {

    @Autowired
    private StudyService studyService;

    /**
     * 向学习表中插入数据
     * 并上传图片
     */
    @RequestMapping("/addNewItem")
    @Transactional
    public StudyVO addNewItem(@RequestBody Study item){
        if (item == null){
            return StudyVO.FAIL;
        }
        else {
            item.setPubdate(DateUtils.getCurrentDateTimeString());
            int i = studyService.addNewItem(item);
            // count 成功插入的数据数量
            int count = 0;
            // 将图片插入到数据库中
            for (String imgUrl : item.getImgUrls()) {
                StudyImg img = new StudyImg();
                img.setSid(item.getSid());
                img.setImgSrc(ParamsConfig.IMG_UPLOAD_PATH + imgUrl);
                count += studyService.insertImage(img);
            }
            if (i == 1 && count == item.getImgUrls().size()){
                return new StudyVO(200, "success", item.getSid());
            }
            else {
                return StudyVO.FAIL;
            }
        }
    }

    /**
     * 查询所有信息(分页查询)
     */
    @RequestMapping("/selectAll")
    public StudyVO selectAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = ParamsConfig.PAGE_ITEMS_SIZE+"") int pageSize){
        List<Study> studies = studyService.selectAll(page, pageSize);
        return new StudyVO(200, "success", studies);
    }

    /**
     * 查询所有项目数量
     */
    @RequestMapping("selectAllItemCount")
    public StudyVO selectAllItemCount(){
        int i = studyService.selectAllItemCount();
        return new StudyVO(200, "success", i);
    }

    /**
     * 通过sid查询该学习订单的所有信息 并补全图片路径信息
     */
    @RequestMapping("selectDetailsBySid")
    public StudyVO selectDetailsBySid(@RequestParam int sid){
        Study study = studyService.selectDetailsBySid(sid);
        List<String> newImgList = new ArrayList<>();
        for (String imgUrl : study.getImgUrls()) {
            newImgList.add(ParamsConfig.BASE_PATH + imgUrl);
        }
        study.setImgUrls(newImgList);
        return new StudyVO(200, "success", study);
    }

    /**
     * 通过sid查询当前项目的评论信息及回复信息 分页查询
     */
    @RequestMapping("/selectDiscussBySid")
    public StudyVO selectDiscussBySid(@RequestParam int sid){
        List<StudyDiscuss> studyDiscusses = studyService.selectDiscussBySid(sid);
        // 补全所查询信息里面的头像路径
        if (studyDiscusses != null) {
            for (StudyDiscuss discuss : studyDiscusses) {
                if (discuss.getCommentUser() != null && !discuss.getCommentUser().getAvatar().contains(ParamsConfig.BASE_PATH)) {
                    discuss.getCommentUser().setAvatar(ParamsConfig.BASE_PATH + discuss.getCommentUser().getAvatar());
                }
                if (discuss.getChildrenList() != null) {
                    for (StudyReply reply : discuss.getChildrenList()) {
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
        return new StudyVO(200, "success", studyDiscusses);
    }

    /**
     * 通过sid查询当前界面所拥有的数量评论数量
     */
    @RequestMapping("/selectDiscussCountBySid")
    public StudyVO selectDiscussCountBySid(@RequestParam int sid){
        int i = studyService.selectDiscussCountBySid(sid);
        return new StudyVO(200, "success", i);
    }
}
