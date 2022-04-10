package com.hebust.service.imple;

import com.hebust.entity.errand.Errand;
import com.hebust.mapper.ErrandMapper;
import com.hebust.service.ErrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
