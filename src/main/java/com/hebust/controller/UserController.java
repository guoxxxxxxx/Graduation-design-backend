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
            // 判断是否已经注册过了
            int key = userService.selectByEmail(user.getEmail());
            if (key == 1){
                return new UserVO(401, "fail", null);
            } else {
                int i = userService.insert(user);
                if (i > 0){
                    return new UserVO(200, "success", null);
                }
                else {
                    return new UserVO(400, "fail", null);
                }
            }
        }else {
            return new UserVO(400, "fail", null);
        }
    }

    /**
     * 用户密码找回
     */
    @RequestMapping("/forget_password")
    public UserVO forget_password(@RequestBody User user){
        if (user != null && !user.getEmail().equals("") && user.getEmail() != null){
            // 先检查邮箱是否存在在数据库中
            int isExist = userService.selectByEmail(user.getEmail());
            if (isExist == 1){
                String new_password = user.getPassword();
                user = userService.selectAllByEmail(user.getEmail());
                user.setPassword(new_password);
                int i = userService.updateByPrimaryKeySelective(user);
                if (i >= 1){
                    return new UserVO(200, "success", null);
                }
                else {
                    return new UserVO(400, "fail", null);
                }
            }
            else{
                // 邮箱不存在数据库中
                return new UserVO(401, "fail", null);
            }
        }
        else {
            return new UserVO(400, "fail", null);
        }
    }
}
