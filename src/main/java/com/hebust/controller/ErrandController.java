package com.hebust.controller;

import com.hebust.entity.errand.Errand;
import com.hebust.entity.errand.ErrandVO;
import com.hebust.service.ErrandService;
import com.hebust.service.relation.ErrandImgService;
import com.hebust.utils.DateUtils;
import com.hebust.utils.SimplifyDetailsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/errand")
public class ErrandController {

    @Autowired
    private ErrandService errandService;
    @Autowired
    private ErrandImgService errandImgService;

    /**
     * 查询所有跑腿订单
     */
    @RequestMapping("/queryAll")
    public ErrandVO queryAll(){
        List<Errand> errands = errandService.selectAll();
        // 精简一下详细信息，设置为 35 个字符 + ... 的格式
        for (Errand errand : errands) {
           errand.setDetails(SimplifyDetailsUtils.simplifyDetails(errand.getDetails()));
        }
        return new ErrandVO(200, "success", errands);
    }

    /**
     * 根据eid查询订单详细信息
     */
    @RequestMapping("/queryDetailsByEid")
    public ErrandVO queryDetailsByEid(@RequestParam int eid){
        Errand errand = errandService.queryDetailsByEid(eid);
        return new ErrandVO(200, "success", errand);
    }

    /**
     * 根据分类查询跑腿订单项目
     */
    @RequestMapping("/queryItemByCategory")
    public ErrandVO queryItemByCategory(@RequestParam String category){
        List<Errand> errands = errandService.queryItemByCategory(category);
        for (Errand errand : errands) {
            errand.setDetails(SimplifyDetailsUtils.simplifyDetails(errand.getDetails()));
        }
        return new ErrandVO(200, "success", errands);
    }

    /**
     * 添加新的跑腿订单
     * 并上传图片
     */
    @RequestMapping("addErrandItem")
    @Transactional
    public ErrandVO addErrandItem(@RequestBody Errand errand){
        if (errand == null){
            return new ErrandVO(400, "对象不存在", null);
        }
        else{
            // 将当前时间添加到对象中
            errand.setPubdate(DateUtils.getCurrentDate());
            errand.setPubtime(DateUtils.getCurrentTime());
            // 向数据库中添加跑腿订单
            int i = errandService.addErrandItem(errand);
            // 将图片插入到数据库中
            int count = errandImgService.insertErrandImg(errand.getEid(), errand.getImgUrls());

            if (i == 1 && count == errand.getImgUrls().size()){
                return new ErrandVO(200, "success", null);
            }
            else {
                return new ErrandVO(400, "fail", null);
            }
        }
    }

    /**
     * 更新接单用户id
     */
    @RequestMapping("/updateEUid")
    public ErrandVO updateUid(@RequestBody Errand errand){
        if (errand == null){
            return new ErrandVO(400, "fail", null);
        }
        else {
            int i = errandService.updateTakeOrdersUid(errand);
            if (i == 1){
                return new ErrandVO(200, "success", null);
            }
            else {
                return ErrandVO.FAIL;
            }
        }
    }

    /**
     * 伪删除跑腿订单信息及其对应的图片
     */
    @RequestMapping("/fakeDeleteItemByEid")
    @Transactional
    public ErrandVO fakeDeleteItemByEid(@RequestBody Errand errand){
        if (errand == null){
            return ErrandVO.FAIL;
        }
        else {
            // 伪删除订单信息
            int i = errandService.fakeDeleteItemByEid(errand.getEid());
            // 伪删除订单对应的图片信息
            errandImgService.fakeDeleteImgByEid(errand.getEid());
            if (i == 1){
                return ErrandVO.SUCCESS;
            }
            else {
                return ErrandVO.FAIL;
            }
        }
    }

    /**
     * 根据eid更新跑腿订单信息
     */
    @RequestMapping("/updateErrandItemByEid")
    public ErrandVO updateErrandItem(@RequestBody Errand errand){
        if (errand == null){
            return ErrandVO.FAIL;
        }
        else{
            int i = errandService.updateErrandItemByEid(errand);
            // 将图片插入到数据库中
            int count = errandImgService.insertErrandImg(errand.getEid(), errand.getImgUrls());
            if (i == 1){
                return ErrandVO.SUCCESS;
            }
            else {
                return ErrandVO.FAIL;
            }
        }
    }

    /**
     * 根据src_img伪删除图片
     */
    @RequestMapping("fakeDeleteImgByImgSrc")
    public ErrandVO fakeDeleteImgByImgSrc(@RequestParam String img_src){
        if (img_src == null){
            return ErrandVO.FAIL;
        }else {
            int i = errandImgService.fakeDeleteImgByImgSrc(img_src);
            if (i == 1){
                return ErrandVO.SUCCESS;
            }
            else {
                return ErrandVO.FAIL;
            }
        }
    }

    /**
     * 根据eid设置订单完成状态
     */
    @RequestMapping("updateErrandIsAchieveStateByEid")
    public ErrandVO updateErrandIsAchieveStateByEid(@RequestParam int eid){
        if (eid == 0){
            return ErrandVO.FAIL;
        }
        else{
            int i = errandService.updateErrandIsAchieveStateByEid(eid);
            if (i == 1){
                return ErrandVO.SUCCESS;
            }
            else {
                return ErrandVO.FAIL;
            }
        }
    }

}
