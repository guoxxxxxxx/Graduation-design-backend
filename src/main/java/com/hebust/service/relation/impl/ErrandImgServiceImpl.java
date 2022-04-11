package com.hebust.service.relation.impl;

import com.hebust.entity.errand.ErrandImg;
import com.hebust.mapper.relation.ErrandImgMapper;
import com.hebust.service.relation.ErrandImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrandImgServiceImpl implements ErrandImgService {

    @Autowired
    private ErrandImgMapper errandImgMapper;

    /**
     * 图片上传的二级路径
     */
    private static final String IMG_UPLOAD_PATH = "/img/";

    @Override
    public int insertErrandImg(int eid, List<String> paths) {
        int count = 0;
        for (String path : paths) {
            ErrandImg errandImg = new ErrandImg();
            errandImg.setEid(eid);
            errandImg.setImgSrc(IMG_UPLOAD_PATH + path);
            count += errandImgMapper.insert(errandImg);
        }
        return count;
    }

    @Override
    public int fakeDeleteImgByEid(int eid) {
        return errandImgMapper.fakeDeleteImgByEid(eid);
    }

    @Override
    public int fakeDeleteImgByImgSrc(String img_src) {
        return errandImgMapper.fakeDeleteImgByImgSrc(img_src);
    }
}
