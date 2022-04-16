package com.hebust.service.impl;

import com.github.pagehelper.PageHelper;
import com.hebust.entity.study.Study;
import com.hebust.entity.study.StudyDiscuss;
import com.hebust.entity.study.StudyImg;
import com.hebust.mapper.StudyMapper;
import com.hebust.mapper.relation.StudyDiscussMapper;
import com.hebust.mapper.relation.StudyImgMapper;
import com.hebust.mapper.relation.StudyReplyMapper;
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

    /**
     * 评论操作mapper
     */
    @Autowired
    private StudyDiscussMapper studyDiscussMapper;

    /**
     * 回复操作Mapper
     */
    @Autowired
    private StudyReplyMapper studyReplyMapper;

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

    @Override
    public Study selectDetailsBySid(int sid) {
        return studyMapper.selectByPrimaryKey(sid);
    }

    @Override
    public List<StudyDiscuss> selectDiscussBySid(int sid) {
        return studyDiscussMapper.selectDiscussBySid(sid);
    }

    @Override
    public int setAchieveBySid(int sid) {
        return studyMapper.setAchieveBySid(sid);
    }

    @Override
    public int selectDiscussCountBySid(int sid) {
        return studyDiscussMapper.selectDiscussCountBySid(sid);
    }

    @Override
    public int fakeDeleteBySid(int sid) {
        // 伪删除回复表中的数据
        studyReplyMapper.fakeDeleteReplyBySid(sid);
        // 伪删除评论表中的数据
        studyDiscussMapper.fakeDeleteDiscussBySid(sid);
        return studyMapper.fakeDeleteBySid(sid);
    }

    @Override
    public int insertDiscuss(StudyDiscuss discuss) {
        return studyDiscussMapper.insertDiscuss(discuss);
    }

    @Override
    public int fakeDeleteImgByFilename(String filename) {
        return studyImgMapper.fakeDeleteImgByFilename(filename);
    }

    @Override
    public int updateBySid(Study study) {
        return studyMapper.updateBySid(study);
    }
}
