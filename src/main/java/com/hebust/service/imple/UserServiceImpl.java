package com.hebust.service.imple;

import com.hebust.entity.user.User;
import com.hebust.mapper.UserMapper;
import com.hebust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int deleteByPrimaryKey(Integer uid) {
        return userMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
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
    public int updateAvatarById(User user) {
        return userMapper.updateAvatarById(user);
    }
}
