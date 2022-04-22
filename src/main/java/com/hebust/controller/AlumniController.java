package com.hebust.controller;

import com.hebust.entity.alumni.AlumniDiscuss;
import com.hebust.entity.alumni.AlumniReply;
import com.hebust.entity.alumni.AlumniVO;
import com.hebust.service.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alumni")
public class AlumniController {

    @Autowired
    private AlumniService alumniService;

    /**
     * 插入新评论
     */
    @RequestMapping("/sendDiscuss")
    public AlumniVO sendDiscuss(@RequestBody AlumniDiscuss discuss){
        int i = alumniService.sendDiscuss(discuss);
        return AlumniVO.SUCCESS;
    }

    /**
     * 插入新回复
     */
    @RequestMapping("/sendReply")
    public AlumniVO sendReply(@RequestBody AlumniReply reply){
        int i = alumniService.sendReply(reply);
        return AlumniVO.SUCCESS;
    }

    /**
     * 分页模糊查询信息
     */
    @RequestMapping("/queryDiscuss")
    public AlumniVO queryDiscuss(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String fuzzyParam){
        List<AlumniDiscuss> alumniDiscusses = alumniService.queryDiscuss(fuzzyParam, page);
        return AlumniVO.SUCCESS(alumniDiscusses);
    }

    /**
     * 查询符合条件的评论数量
     */
    @RequestMapping("/queryDiscussCount")
    public AlumniVO queryDiscussCount(@RequestParam(defaultValue = "") String fuzzyParam){
        int i = alumniService.queryDiscussCount(fuzzyParam);
        return AlumniVO.SUCCESS(i);
    }
}
