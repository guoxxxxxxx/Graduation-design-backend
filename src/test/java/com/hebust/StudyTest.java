package com.hebust;

import com.hebust.entity.study.Study;
import com.hebust.mapper.StudyMapper;
import com.hebust.service.StudyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyTest {

    @Autowired
    private StudyService studyService;

    /**
     * 测试查询所有功能是否正确
     */
    @Test
    public void t1(){
        List<Study> studies = studyService.selectAll(1, 1);
        for (Study study : studies) {
            System.out.println(study);
        }
    }
}
