package com.nut2014.mapper;


import com.nut2014.base.BaseMapper;
import com.nut2014.entity.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

/**
 * 配置版实现
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    User getUserByUserName(String userName);
}

