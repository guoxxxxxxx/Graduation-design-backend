package com.hebust;

import com.hebust.entity.errand.Errand;
import com.hebust.mapper.relation.ErrandReplyMapper;
import com.hebust.service.ErrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErrandTest {

    @Autowired
    private ErrandService errandService;

    @Autowired
    private ErrandReplyMapper replyMapper;

    @Test
    public void t1(){
        List<Errand> errands = errandService.selectAll();
        for (Errand errand : errands) {
            System.out.println(errand);
        }
    }

    @Test
    public void fake(){
        int i = replyMapper.fakeDeleteByEid(122);
        System.out.println(i);
    }
}
