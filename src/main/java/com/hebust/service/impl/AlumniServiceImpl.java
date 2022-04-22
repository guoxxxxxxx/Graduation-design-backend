package com.hebust.service.impl;

import com.github.pagehelper.PageHelper;
import com.hebust.config.ParamsConfig;
import com.hebust.entity.alumni.AlumniDiscuss;
import com.hebust.entity.alumni.AlumniReply;
import com.hebust.entity.trade.TradeDiscuss;
import com.hebust.entity.trade.TradeReply;
import com.hebust.mapper.AlumniDiscussMapper;
import com.hebust.mapper.relation.AlumniReplyMapper;
import com.hebust.service.AlumniService;
import com.hebust.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AlumniServiceImpl implements AlumniService {

    @Autowired
    AlumniDiscussMapper discussMapper;
    @Autowired
    AlumniReplyMapper replyMapper;

    @Override
    public int sendDiscuss(AlumniDiscuss discuss) {
        // 补全日期信息
        discuss.setCreateDate(DateUtils.getCurrentDateTimeString());
        return discussMapper.sendDiscuss(discuss);
    }

    @Override
    public int sendReply(AlumniReply reply) {
        // 补全回复信息
        reply.setCreateDate(DateUtils.getCurrentDateTimeString());
        return replyMapper.sendReply(reply);
    }

    @Override
    public List<AlumniDiscuss> queryDiscuss(String fuzzyParam, int page) {
        PageHelper.startPage(page, ParamsConfig.PAGE_DISCUSS_SIZE);
        List<AlumniDiscuss> alumniDiscusses = discussMapper.queryDiscuss(fuzzyParam, page);
        // 补全用户头像信息
        if (alumniDiscusses != null) {
            for (AlumniDiscuss discuss : alumniDiscusses) {
                if (discuss.getCommentUser() != null && !discuss.getCommentUser().getAvatar().contains(ParamsConfig.BASE_PATH)) {
                    discuss.getCommentUser().setAvatar(ParamsConfig.BASE_PATH + discuss.getCommentUser().getAvatar());
                }
                if (discuss.getChildrenList() != null) {
                    for (AlumniReply reply : discuss.getChildrenList()) {
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
        return alumniDiscusses;
    }

    @Override
    public int queryDiscussCount(String fuzzyParam) {
        return discussMapper.queryDiscussCount(fuzzyParam);
    }
}
