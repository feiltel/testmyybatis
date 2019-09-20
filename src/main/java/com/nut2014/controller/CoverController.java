package com.nut2014.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nut2014.base.BaseController;
import com.nut2014.entity.Cover;
import com.nut2014.pojo.BaseResponse;
import com.nut2014.pojo.PageBaseResponse;
import com.nut2014.service.CoverService;
import com.nut2014.verification.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cover")
public class CoverController implements BaseController<Cover> {
    @Autowired
    CoverService dataService;


    @RequestMapping("/add")
    @Override
    @ResponseBody
    public BaseResponse<String> add(@RequestBody Cover cover) {
        if (cover == null) {
            return new BaseResponse<>(0, "失败", "");
        }
        int sqlCode = dataService.add(cover);
        return new BaseResponse<>(sqlCode, sqlCode > 0 ? "成功" : "失败", "");
    }

    @RequestMapping("/delete")
    @Override
    public BaseResponse<String> delete(int id) {
        int sqlCode = dataService.delete(id);
        return new BaseResponse<>(sqlCode, sqlCode > 0 ? "成功" : "失败", "");
    }

    @RequestMapping("/update")
    @Override
    public BaseResponse<String> update(Cover cover) {
        int sqlCode = dataService.update(cover);
        return new BaseResponse<>(sqlCode, sqlCode > 0 ? "成功" : "失败", "");
    }

    @RequestMapping("/get")
    @Override
    public BaseResponse<Cover> get(int id) {
        Cover cover = dataService.get(id);
        return new BaseResponse<>(cover != null ? 1 : 0, cover != null ? "成功" : "失败", cover);
    }

    @RequestMapping("/getAll")
    @Override
    public BaseResponse<List<Cover>> getAll() {
        List<Cover> coverList = dataService.getAll();
        return new BaseResponse<>(coverList != null ? 1 : 0, coverList != null ? "成功" : "失败", coverList);
    }

    @UserLoginToken
    @RequestMapping("/getAllPage")
    @Override
    public PageBaseResponse<List<Cover>> getAllPage(int pageNum) {
        //pageNum:表示第几页  pageSize:表示一页展示的数据
        PageHelper.startPage(pageNum, 20);
        List<Cover> list = dataService.getCoverInfo();
        //将查询到的数据封装到PageInfo对象
        PageInfo<Cover> pageInfo = new PageInfo(list, 3);
        return new PageBaseResponse<>(1, "success", pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPages());
    }

    /*@UserLoginToken*/
    @RequestMapping("/getAllUserPage")
    public PageBaseResponse<List<Cover>> getAllUserPage(int userId,int pageNum) {
        //pageNum:表示第几页  pageSize:表示一页展示的数据
        PageHelper.startPage(pageNum, 20);
        List<Cover> list = dataService.getUserCoverInfo(userId);
        //将查询到的数据封装到PageInfo对象
        PageInfo<Cover> pageInfo = new PageInfo(list, 3);
        return new PageBaseResponse<>(1, "success", pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPages());
    }


    @RequestMapping("/getCoverInfo")
    public BaseResponse<List<Cover>> getCoverInfo() {
        List<Cover> coverList = dataService.getCoverInfo();
        return new BaseResponse<>(coverList != null ? 1 : 0, coverList != null ? "成功" : "失败", coverList);
    }


}
