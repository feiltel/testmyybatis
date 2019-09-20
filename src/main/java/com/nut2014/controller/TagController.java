package com.nut2014.controller;

import com.nut2014.base.BaseController;
import com.nut2014.entity.Tag;
import com.nut2014.pojo.BaseResponse;
import com.nut2014.pojo.PageBaseResponse;
import com.nut2014.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController implements BaseController<Tag> {
    @Autowired
    TagService tagService;

    @Override
    @RequestMapping("/add")
    public BaseResponse<String> add(Tag tag) {
        int flag = tagService.add(tag);
        return new BaseResponse<>(0, "success", null);
    }

    @Override
    public BaseResponse<String> delete(int id) {
        return null;
    }

    @Override
    public BaseResponse<String> update(Tag tag) {
        return null;
    }

    @Override
    public BaseResponse<Tag> get(int id) {
        return null;
    }

    @Override
    @RequestMapping("/getAll")
    public BaseResponse<List<Tag>> getAll() {
        List<Tag> allTag = tagService.getAll();
        allTag.add(0,new Tag("请选择",0));
        return new BaseResponse<>(1, "success",allTag);
    }

    @Override
    public PageBaseResponse<List<Tag>> getAllPage(int pageNum) {
        return null;
    }
}
