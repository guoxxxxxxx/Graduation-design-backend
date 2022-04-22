package com.hebust.mapper.relation;

import com.hebust.entity.lostProperty.LostImg;

import java.util.List;

public interface LostImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LostImg record);

    int insertSelective(LostImg record);

    LostImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LostImg record);

    int updateByPrimaryKey(LostImg record);

    /**
     * 上传图片到数据库中
     */
    int uploadImg(LostImg img);

    /**
     * 通过lid查询与之对应的图片信息
     */
    List<String> queryImgs(int lid);

    /**
     * 伪删除图片
     */
    int fakeDeleteImg(String imgSrc);
}