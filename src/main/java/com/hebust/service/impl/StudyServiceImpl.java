package com.hebust.service.impl;

import com.github.pagehelper.PageHelper;
import com.hebust.entity.study.Study;
import com.hebust.entity.study.StudyImg;
import com.hebust.mapper.StudyMapper;
import com.hebust.mapper.relation.StudyImgMapper;
import com.hebust.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyServiceImpl implements StudyService {

    /**
     * 基础功能
     */
    @Autowired
    private StudyMapper studyMapper;

    /**
     * 图片操作Mapper
     */
    @Autowired
    private StudyImgMapper studyImgMapper;

    @Override
    public int addNewItem(Study study) {
        return studyMapper.insertSelective(study);
    }

    @Override
    public List<Study> selectAll(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Study> studyList = studyMapper.selectAll();
        return studyList;
    }

    @Override
    public int selectAllItemCount() {
        return studyMapper.selectAllItemCount();
    }

    @Override
    public List<String> selectAllImgBySid(int sid) {
        return studyImgMapper.selectAllImgBySid(sid);
    }

    @Override
    public int insertImage(StudyImg record) {
        return studyImgMapper.insertImage(record);
    }
}
