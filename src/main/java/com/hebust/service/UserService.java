package com.hebust.service;

import com.hebust.entity.user.User;

public interface UserService {

    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    String selectPasswordByEmail(String email);
}