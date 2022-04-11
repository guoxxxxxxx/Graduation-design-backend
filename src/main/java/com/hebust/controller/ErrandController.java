package com.hebust.controller;

import com.hebust.entity.errand.Errand;
import com.hebust.entity.errand.ErrandVO;
import com.hebust.service.ErrandService;
import com.hebust.utils.DateUtils;
import com.hebust.utils.SimplifyDetailsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
     */
    @RequestMapping("addErrandItem")
    public ErrandVO addErrandItem(@RequestBody Errand errand){
        if (errand == null){
            return new ErrandVO(400, "对象不存在", null);
        }
        else{
            // 将当前时间添加到对象中
            errand.setPubdate(DateUtils.getCurrentDate());
            errand.setPubtime(DateUtils.getCurrentTime());
            int i = errandService.addErrandItem(errand);
            if (i == 1){
                return new ErrandVO(200, "success", null);
            }
            else {
                return new ErrandVO(400, "fail", null);
            }
        }
    }

}
