package com.hebust.controller;

import com.hebust.entity.errand.Errand;
import com.hebust.entity.errand.ErrandVO;
import com.hebust.service.ErrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            if (errand.getDetails().length() > 35)
                errand.setDetails(errand.getDetails().substring(0, 35) + "...");
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

}
