package com.hebust.controller;

import com.hebust.entity.user.User;
import com.hebust.entity.user.UserVO;
import com.hebust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public UserVO login(@RequestBody User user) {
        if (user.getEmail().equals("") || user.getEmail() == null) {
            return new UserVO(400, "fail", null);
        } else {
            return new UserVO(200, "success", userService.selectPasswordByEmail(user.getEmail()));
        }
    }

}
