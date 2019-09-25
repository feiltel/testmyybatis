package com.nut2014.service;


import com.nut2014.base.BaseService;
import com.nut2014.entity.User;
import com.nut2014.mapper.UserMapper;
import com.nut2014.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements BaseService<User> {
    @Autowired
    // UserDao dataMapper;
            UserMapper dataMapper;


    @Override
    public int add(User user) {
        String passStr = MyUtils.MD5encode(user.getUserName() + user.getPassWord());
        user.setPassWord(passStr);
        return dataMapper.add(user);
    }

    @Override
    public int delete(int id) {
        return dataMapper.delete(id);
    }

    @Override
    public int update(User user) {
        return dataMapper.update(user);
    }

    @Override
    public User get(int id) {
        return dataMapper.get(id);
    }

    @Override
    public List<User> getAll() {
        return dataMapper.getAll();
    }

    public User getUser(String userName) {
        return dataMapper.getUserByUserName(userName);
    }
}