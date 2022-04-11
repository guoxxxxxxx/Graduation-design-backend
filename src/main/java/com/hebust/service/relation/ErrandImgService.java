package com.hebust.service.relation;

import com.hebust.entity.errand.Errand;

import java.util.List;

public interface ErrandImgService {

    /**
     *  向数据库中插入图片路径
     */
    int insertErrandImg(int eid, List<String> paths);

    /**
     * 通过eid伪删除与其对应的图片
     */
    int fakeDeleteImgByEid(int eid);

    /**
     * 根据img_src伪删除图片信息
     */
    int fakeDeleteImgByImgSrc(String img_src);
}
