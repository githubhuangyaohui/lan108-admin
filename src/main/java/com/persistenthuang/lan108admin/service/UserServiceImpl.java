package com.persistenthuang.lan108admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.persistenthuang.lan108admin.mapper.UserMapper;
import com.persistenthuang.lan108admin.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public boolean isExistByName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        User user = userMapper.selectOne(wrapper);
        return null != user;
    }

    @Override
    public boolean isExistByEmail(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_email", email);
        User user = userMapper.selectOne(wrapper);
        return null != user;
    }

    @Override
    public boolean isExistByID(int id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        User user = userMapper.selectOne(wrapper);
        return null != user;
    }

    @Override
    public boolean isAuthor(int id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        User user = userMapper.selectOne(wrapper);
        return user.getUserAuthorId() == 0;
    }

    @Override
    public int getAuthorID(int id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        User user = userMapper.selectOne(wrapper);
        return user.getUserAuthorId();
    }

    @Override
    public User getByName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id", "user_author_id", "user_name", "user_sex", "user_email", "user_level", "user_role");
        wrapper.eq("user_name", username);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public User getAllMessageByName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", username);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public User getUserById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public void updateAuthor(User user) {
        this.userMapper.updateById(user);
    }
}
