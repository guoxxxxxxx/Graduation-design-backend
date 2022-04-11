package com.hebust.mapper.relation;

import com.hebust.entity.errand.Errand;
import com.hebust.entity.errand.ErrandImg;

import java.util.List;

public interface ErrandImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ErrandImg record);

    int insertSelective(ErrandImg record);

    ErrandImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ErrandImg record);

    int updateByPrimaryKey(ErrandImg record);

    /**
     * 通过eid查询所有对应的图片
     */
    List<ErrandImg> selectAllByEid(Integer eid);

    /**
     * 将上传的图片名称保存到数据库中
     */
    int insertImgPath(String filename);
}