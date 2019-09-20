package com.nut2014.service;


import com.nut2014.base.BaseService;
import com.nut2014.entity.Cover;
import com.nut2014.entity.User;
import com.nut2014.mapper.CoverMapper;
import com.nut2014.mapper.UserDao;
import com.nut2014.mapper.UserMapper;
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

    public User getUser(String userName){
        return dataMapper.getUserByUserName(userName);
    }
}