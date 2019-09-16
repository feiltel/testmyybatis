package com.nut2014.mapper;

import com.nut2014.base.BaseMapper;
import com.nut2014.entity.Cover;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoverMapper extends BaseMapper<Cover> {
    List<Cover> getCoverInfo();
}
