package com.hebust.mapper.relation;

import com.hebust.entity.errand.Errand;
import com.hebust.entity.errand.ErrandImg;

import java.util.List;

public interface ErrandImgMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 将上传的图片名称保存到数据库中
     */
    int insert(ErrandImg record);

    int insertSelective(ErrandImg record);

    ErrandImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ErrandImg record);

    int updateByPrimaryKey(ErrandImg record);

    /**
     * 通过eid查询所有对应的图片
     */
    List<String> selectAllByEid(Integer eid);

    /**
     * 通过eid伪删除与之对应的图片信息
     */
    int fakeDeleteImgByEid(int eid);

    /**
     * 根据图片的名称伪删除图片
     */
    int fakeDeleteImgByImgSrc(String img_src);

}