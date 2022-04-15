package com.hebust.controller;

import com.hebust.config.ParamsConfig;
import com.hebust.entity.study.Study;
import com.hebust.entity.study.StudyImg;
import com.hebust.entity.study.StudyVO;
import com.hebust.entity.user.User;
import com.hebust.service.StudyService;
import com.hebust.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/study")
public class StudyController {

    @Autowired
    private StudyService studyService;

    /**
     * 向学习表中插入数据
     * 并上传图片
     */
    @RequestMapping("/addNewItem")
    @Transactional
    public StudyVO addNewItem(@RequestBody Study item){
        if (item == null){
            return StudyVO.FAIL;
        }
        else {
            item.setPubdate(DateUtils.getCurrentDateTime());
            int i = studyService.addNewItem(item);
            // count 成功插入的数据数量
            int count = 0;
            // 将图片插入到数据库中
            for (String imgUrl : item.getImgUrls()) {
                StudyImg img = new StudyImg();
                img.setSid(item.getSid());
                img.setImgSrc(ParamsConfig.IMG_UPLOAD_PATH + imgUrl);
                count += studyService.insertImage(img);
            }
            if (i == 1 && count == item.getImgUrls().size()){
                return StudyVO.SUCCESS;
            }
            else {
                return StudyVO.FAIL;
            }
        }
    }

    /**
     * 查询所有信息(分页查询)
     */
    @RequestMapping("/selectAll")
    public StudyVO selectAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = ParamsConfig.PAGE_ITEMS_SIZE+"") int pageSize){
        List<Study> studies = studyService.selectAll(page, pageSize);
        return new StudyVO(200, "success", studies);
    }

    /**
     * 查询所有项目数量
     */
    @RequestMapping("selectAllItemCount")
    public StudyVO selectAllItemCount(){
        int i = studyService.selectAllItemCount();
        return new StudyVO(200, "success", i);
    }




    /**
     * 测试
     */
    @RequestMapping("/test")
    public void test(@RequestBody User user, @RequestParam(defaultValue = "1") int page){
        System.out.println(user);
        System.out.println("page = " + page);
    }
}
