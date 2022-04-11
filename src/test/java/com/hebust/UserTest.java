package com.hebust;

import com.hebust.entity.user.User;
import com.hebust.service.UserService;
import com.hebust.utils.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void insertTest(){
        User user = new User();
        user.setEmail("1234567");
        user.setPassword("111111");
        int insert = userService.insert(user);
        System.out.println(insert);
    }

    @Test
    public void t2(){
        int i = userService.selectByEmail("666@123.com");
        System.out.println(i);
    }

    @Test
    public void uuidT(){
        String uuid = UUIDUtils.getUUID();
        System.out.println(uuid);
    }

    @Test
    public void t(){
        User user = userService.selectByPrimaryKey(2);
        System.out.println(user);
    }
}
