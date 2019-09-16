package com.nut2014.service;

import com.nut2014.base.BaseService;
import com.nut2014.entity.Cover;
import com.nut2014.entity.MyLog;
import com.nut2014.mapper.CoverMapper;
import com.nut2014.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyLogService implements BaseService<MyLog> {

    @Autowired
    LogMapper dataMapper;

    @Override
    public int add(MyLog cover) {
        return dataMapper.add(cover);
    }

    @Override
    public int delete(int id) {
        return dataMapper.delete(id);
    }

    @Override
    public int update(MyLog cover) {
        return dataMapper.update(cover);
    }

    @Override
    public MyLog get(int id) {
        return dataMapper.get(id);
    }

    @Override
    public List<MyLog> getAll() {
        return dataMapper.getAll();
    }

}
