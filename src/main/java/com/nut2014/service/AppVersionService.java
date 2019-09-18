package com.nut2014.service;

import com.nut2014.base.BaseService;
import com.nut2014.entity.AppVersion;
import com.nut2014.mapper.AppVersionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppVersionService implements BaseService<AppVersion> {
    @Autowired
    AppVersionMapper versionMapper;

    @Override
    public int add(AppVersion appVersion) {
        return versionMapper.add(appVersion);
    }

    @Override
    public int delete(int id) {
        return versionMapper.delete(id);
    }

    @Override
    public int update(AppVersion appVersion) {
        return versionMapper.update(appVersion);
    }

    @Override
    public AppVersion get(int id) {
        return versionMapper.get(id);
    }

    @Override
    public List<AppVersion> getAll() {
        return versionMapper.getAll();
    }
}
