package com.hebust.service.impl;

import com.hebust.entity.errand.Errand;
import com.hebust.mapper.ErrandMapper;
import com.hebust.mapper.relation.ErrandImgMapper;
import com.hebust.service.ErrandService;
import com.hebust.service.relation.ErrandImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ErrandServiceImpl implements ErrandService {

    @Autowired
    private ErrandMapper errandMapper;

    @Override
    public List<Errand> selectAll() {
        return errandMapper.selectAll();
    }

    @Override
    public Errand queryDetailsByEid(int eid) {
        return errandMapper.selectDetailsByEid(eid);
    }

    @Override
    public List<Errand> queryItemByCategory(String category) {
        return errandMapper.selectItemByCategory(category);
    }

    @Override
    @Transactional
    public int addErrandItem(Errand errand) {
        return errandMapper.insertSelective(errand);
    }

    @Override
    public int updateTakeOrdersUid(Errand errand) {
        return errandMapper.updateByPrimaryKeySelective(errand);
    }

    @Override
    public int fakeDeleteItemByEid(int eid) {
        return errandMapper.fakeDeleteItemByEid(eid);
    }

    @Override
    public int updateErrandItemByEid(Errand errand) {
        return errandMapper.updateErrandItemByEid(errand);
    }

    @Override
    public int updateErrandIsAchieveStateByEid(int eid) {
        return errandMapper.updateErrandIsAchieveStateByEid(eid);
    }
}
