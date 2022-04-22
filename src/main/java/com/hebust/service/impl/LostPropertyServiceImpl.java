package com.hebust.service.impl;

import com.github.pagehelper.PageHelper;
import com.hebust.config.ParamsConfig;
import com.hebust.entity.QueryCondition;
import com.hebust.entity.lostProperty.LostDiscuss;
import com.hebust.entity.lostProperty.LostImg;
import com.hebust.entity.lostProperty.LostProperty;
import com.hebust.entity.lostProperty.LostReply;
import com.hebust.mapper.LostPropertyMapper;
import com.hebust.mapper.relation.LostDiscussMapper;
import com.hebust.mapper.relation.LostImgMapper;
import com.hebust.mapper.relation.LostReplyMapper;
import com.hebust.service.LostPropertyService;
import com.hebust.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LostPropertyServiceImpl implements LostPropertyService {

    @Autowired
    private LostPropertyMapper lostPropertyMapper;

    @Autowired
    private LostImgMapper imgMapper;

    @Autowired
    private LostDiscussMapper discussMapper;

    @Autowired
    private LostReplyMapper replyMapper;

    @Override
    public int publishNewItem(LostProperty property) {
        property.setPubdate(DateUtils.getCurrentDateTimeString());
        return lostPropertyMapper.publishNewItem(property);
    }

    @Override
    public int uploadImg(LostImg img) {
        return imgMapper.uploadImg(img);
    }

    @Override
    public List<LostProperty> queryByCondition(QueryCondition condition) {
        PageHelper.startPage(condition.getPage(), ParamsConfig.PAGE_ITEMS_SIZE);
        return lostPropertyMapper.queryByCondition(condition);
    }

    @Override
    public int queryCountByCondition(QueryCondition condition) {
        return lostPropertyMapper.queryCountByCondition(condition);
    }

    @Override
    public LostProperty queryDetailsByLid(int lid) {
        LostProperty lostProperty = lostPropertyMapper.queryDetailsByLit(lid);
        List<String> newImgUrl = new ArrayList<>();
        for (String imgUrl : lostProperty.getImgUrls()) {
            if (!imgUrl.contains(ParamsConfig.BASE_PATH)){
                newImgUrl.add(ParamsConfig.BASE_PATH + imgUrl);
            }
            else {
                newImgUrl.add(imgUrl);
            }
        }
        lostProperty.setImgUrls(newImgUrl);
        return lostProperty;
    }

    @Override
    public int updateIsAchieveByLid(int lid) {
        return lostPropertyMapper.updateIsAchieveByLid(lid);
    }

    @Override
    public int fakeDeleteByLid(int lid) {
        return lostPropertyMapper.fakeDeleteByLid(lid);
    }

    @Override
    public List<String> queryImgByLid(int lid) {
        List<String> imgs = imgMapper.queryImgs(lid);
        // 完善图片路径信息
        List<String> new_imgs = new ArrayList<>();
        for (String img : imgs) {
            if (!img.contains(ParamsConfig.BASE_PATH)){
                new_imgs.add(ParamsConfig.BASE_PATH + img);
            }
        }
        return new_imgs;
    }

    @Override
    public int fakeDeleteImg(String imgSrc) {
        if (imgSrc.contains(ParamsConfig.BASE_PATH)){
            imgSrc = imgSrc.substring(ParamsConfig.BASE_PATH.length());
        }
        return imgMapper.fakeDeleteImg(imgSrc);
    }

    @Override
    public int updateByLid(LostProperty property) {
        // 插入新的图片信息
        LostImg lostImg = new LostImg();
        lostImg.setLid(property.getLid());
        for (String imgUrl : property.getImgUrls()) {
            lostImg.setImgSrc(ParamsConfig.IMG_UPLOAD_PATH + imgUrl);
            imgMapper.uploadImg(lostImg);
        }
        // 更新项目信息
        return lostPropertyMapper.updateByLid(property);
    }

    @Override
    public int queryDiscussCountByLid(int lid) {
        return discussMapper.queryDiscussCountByLid(lid);
    }

    @Override
    public int sendDiscuss(LostDiscuss discuss) {
        discuss.setCreateDate(DateUtils.getCurrentDateTimeString());
        return discussMapper.sendDiscuss(discuss);
    }

    @Override
    public int doSendReply(LostReply reply) {
        reply.setCreateDate(DateUtils.getCurrentDateTimeString());
        return replyMapper.doSendReply(reply);
    }

    @Override
    public List<LostDiscuss> queryDiscussById(int lid, int page) {
        PageHelper.startPage(page, ParamsConfig.PAGE_DISCUSS_SIZE);
        List<LostDiscuss> lostDiscusses = discussMapper.queryDiscussByLid(lid);
        // 补全用户头像信息
        if (lostDiscusses != null) {
            for (LostDiscuss discuss : lostDiscusses) {
                if (discuss.getCommentUser() != null && !discuss.getCommentUser().getAvatar().contains(ParamsConfig.BASE_PATH)) {
                    discuss.getCommentUser().setAvatar(ParamsConfig.BASE_PATH + discuss.getCommentUser().getAvatar());
                }
                if (discuss.getChildrenList() != null) {
                    for (LostReply reply : discuss.getChildrenList()) {
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
        return lostDiscusses;
    }
}
