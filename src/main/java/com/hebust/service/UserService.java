package com.hebust.service;

import com.hebust.entity.user.User;

public interface UserService {

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
     * 查询邮箱是否已经存在在数据库中
     * 1: 存在
     * 0: 未存在
     */
    int selectByEmail(String email);

    /**
     * 根据邮箱查询用户所有信息
     */
    User selectAllByEmail(String email);
}
