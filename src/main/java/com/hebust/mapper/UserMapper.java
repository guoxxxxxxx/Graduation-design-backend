package com.hebust.mapper;

import com.hebust.entity.user.SimplifyUser;
import com.hebust.entity.user.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 通过邮箱获取密码
     */
    String selectPasswordByEmail(String email);

    /**
     * 查询邮箱是否已被注册
     */
    int selectByEmail(String email);

    /**
     * 根据邮箱查询用户所有信息
     */
    User selectAllByEmail(String email);

    /**
     * 通过id修改用户头像信息
     */
    int updateAvatarById(User record);

    /**
     * 通过id查询简化版用户信息
     */
    SimplifyUser selectSimplifyUserById(int id);
}