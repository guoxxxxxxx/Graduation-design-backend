package com.hebust.mapper.relation;

import com.hebust.entity.study.StudyImg;

import java.util.List;

public interface StudyImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudyImg record);

    int insertSelective(StudyImg record);

    StudyImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudyImg record);

    int updateByPrimaryKey(StudyImg record);

    /**
     * 根据sid查询与之对应的所有未删除图片
     */
    List<String> selectAllImgBySid(int sid);

    /**
     * 向数据库中插入图片信息
     */
    int insertImage(StudyImg record);
}