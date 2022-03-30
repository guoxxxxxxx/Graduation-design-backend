package com.hebust.mapper;

import com.hebust.entity.user.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /*
        通过email查询用户密码
     */
    String selectPasswordByEmail(String email);
}