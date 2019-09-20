package com.nut2014.service;

import com.nut2014.base.BaseService;
import com.nut2014.entity.Cover;
import com.nut2014.mapper.CoverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoverService implements BaseService<Cover> {
    @Autowired
    CoverDao coverMapper;
    // CoverMapper coverMapper;


    @Override
    public int add(Cover cover) {
        return coverMapper.add(cover);
    }

    @Override
    public int delete(int id) {
        return coverMapper.delete(id);
    }

    @Override
    public int update(Cover cover) {
        return coverMapper.update(cover);
    }

    @Override
    public Cover get(int id) {
        return coverMapper.get(id);
    }

    @Override
    public List<Cover> getAll() {
        return coverMapper.getAll();
    }

    public List<Cover> getCoverInfo() {
        return coverMapper.getCoverInfo();
    }

}
