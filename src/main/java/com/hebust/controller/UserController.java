package com.hebust.controller;

import com.hebust.entity.user.User;
import com.hebust.entity.user.UserVO;
import com.hebust.service.UserService;
import com.hebust.utils.StatusCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录校验
     */
    @RequestMapping("/login")
    public UserVO login(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().equals("")) {
            return new UserVO(400, "fail", null);
        } else {
            String receivePassword = userService.selectPasswordByEmail(user.getEmail());
//            String receivePassword = "123";
            if (receivePassword != null && receivePassword.equals(user.getPassword())){
                return new UserVO(200, "success", StatusCodeUtils.SUCCESS);
            }
            else {
                // 请求服务器成功 但账户密码输入不正确
                return new UserVO(200, "success", StatusCodeUtils.FAIL);
            }
        }
    }

    /**
     * 用户注册
     */
    @RequestMapping("/register")
    public UserVO register(@RequestBody User user){
        if (user != null && user.getEmail()!=null && !user.getEmail().equals("")){
            int i = userService.insert(user);
            if (i > 0){
                return new UserVO(200, "success", null);
            }
            else {
                return new UserVO(400, "fail", null);
            }
        }else {
            return new UserVO(400, "fail", null);
        }
    }

}
