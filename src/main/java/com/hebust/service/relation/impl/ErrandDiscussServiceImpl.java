package com.hebust.service.relation.impl;

import com.hebust.entity.errand.ErrandDiscuss;
import com.hebust.mapper.relation.ErrandDiscussMapper;
import com.hebust.service.relation.ErrandDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrandDiscussServiceImpl implements ErrandDiscussService {

    @Autowired
    private ErrandDiscussMapper errandDiscussMapper;


    @Override
    public List<ErrandDiscuss> selectAllDiscussByEid(int eid) {
        return errandDiscussMapper.selectAllByEid(eid);
    }
}
