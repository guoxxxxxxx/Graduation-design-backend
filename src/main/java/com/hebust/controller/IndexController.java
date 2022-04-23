package com.hebust.controller;

import com.hebust.entity.other.IndexVO;
import com.hebust.entity.other.Percentage;
import com.hebust.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主页的controller
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    IndexService indexService;

    @RequestMapping("/queryPercentage")
    public IndexVO queryPercentage(){
        Percentage percentage = indexService.queryPercentage();
        return IndexVO.SUCCESS(percentage);
    }
}
