package com.hebust.service.impl;

import com.hebust.entity.user.User;
import com.hebust.mapper.UserMapper;
import com.hebust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int deleteByPrimaryKey(Integer uid) {
        return userMapper.deleteByPrimaryKey(uid);
    }

    @Override
    @Transactional
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public String selectPasswordByEmail(String email) {
        return userMapper.selectPasswordByEmail(email);
    }

    @Override
    public int selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public User selectAllByEmail(String email) {
        return userMapper.selectAllByEmail(email);
    }

    @Override
    @Transactional
    public int updateAvatarById(User user) {
        return userMapper.updateAvatarById(user);
    }

    @Override
    public int queryUserCount() {
        return userMapper.queryUserCount();
    }
}
