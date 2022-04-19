package com.hebust.service.relation.impl;

import com.github.pagehelper.PageHelper;
import com.hebust.config.ParamsConfig;
import com.hebust.entity.errand.ErrandDiscuss;
import com.hebust.entity.errand.ErrandReply;
import com.hebust.mapper.relation.ErrandDiscussMapper;
import com.hebust.mapper.relation.ErrandReplyMapper;
import com.hebust.service.relation.ErrandDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrandDiscussServiceImpl implements ErrandDiscussService {

    @Autowired
    private ErrandDiscussMapper errandDiscussMapper;
    @Autowired
    private ErrandReplyMapper replyMapper;


    @Override
    public List<ErrandDiscuss> selectAllDiscussByEid(int eid, int page) {
        PageHelper.startPage(page, ParamsConfig.PAGE_DISCUSS_SIZE);
        return errandDiscussMapper.selectAllByEid(eid);
    }

    @Override
    public int insertDiscuss(ErrandDiscuss discuss) {
        return errandDiscussMapper.insertDiscuss(discuss);
    }

    @Override
    public int insertReply(ErrandReply reply) {
        return replyMapper.insertReply(reply);
    }
}
