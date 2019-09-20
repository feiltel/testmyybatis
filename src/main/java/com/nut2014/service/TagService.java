package com.nut2014.service;


import com.nut2014.base.BaseService;
import com.nut2014.entity.Tag;
import com.nut2014.mapper.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements BaseService<Tag> {



    @Autowired(required = false)
    TagDao dataMapper;
 


    @Override
    public int add(Tag tag) {
        return dataMapper.add(tag);
    }

    @Override
    public int delete(int id) {
        return dataMapper.delete(id);
    }

    @Override
    public int update(Tag tag) {
        return dataMapper.update(tag);
    }

    @Override
    public Tag get(int id) {
        return dataMapper.get(id);
    }

    @Override
    public List<Tag> getAll() {
        return dataMapper.getAll();
    }
}