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
        // 判断传递过来的邮箱参数是否不为空
        if (user.getEmail() == null || user.getEmail().equals("")) {
            // 为空的话返回400错误状态码
            return new UserVO(400, "fail", null);
        } else {
            // 判断该邮箱是否存在于数据库中(判断是否已经被注册了)
            if (userService.selectByEmail(user.getEmail()) == 1) {
                // 从数据库中读取用户密码
                String receivePassword = userService.selectPasswordByEmail(user.getEmail());
                if (receivePassword != null && receivePassword.equals(user.getPassword())) {
                    // 登录成功
                    return new UserVO(200, "success", null);
                } else {
                    // 登录失败
                    return new UserVO(400, "fail", null);
                }
            }
            else {
                // 未被注册返回401未被注册状态码
                return new UserVO(401, "fail", null);
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
                // 用户已被注册
                return new UserVO(402, "fail", null);
            } else {
                // 将用户插入到数据库中
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
