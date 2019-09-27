package com.nut2014.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nut2014.base.BaseController;
import com.nut2014.config.ConfigResource;
import com.nut2014.entity.User;
import com.nut2014.pojo.BaseResponse;
import com.nut2014.pojo.PageBaseResponse;
import com.nut2014.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController implements BaseController<User> {

    @Autowired
    private UserService dataService;

    @Autowired
    ConfigResource configResource;

    @RequestMapping("/add")
    @Override
    public BaseResponse<String> add(User user) {
        if (user != null) {
            if (user.getAvatarPath() == null || user.getAvatarPath().isEmpty()) {
                user.setAvatarPath(configResource.getDefaultAvatarPath());
            }
            if (user.getBgImg() == null || user.getBgImg().isEmpty()) {
                user.setBgImg(configResource.getDefaulUserBg());
            }
            if (user.getRealName() == null || user.getRealName().isEmpty()) {
                user.setRealName(user.getUserName());
            }
        }
        int sqlCode = dataService.add(user);
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
    public BaseResponse<String> update(User user) {
        int sqlCode = dataService.update(user);
        return new BaseResponse<>(sqlCode, sqlCode > 0 ? "成功" : "失败", "");
    }

    @RequestMapping("/get")
    @Override
    public BaseResponse<User> get(int id) {
        User cover = dataService.get(id);
        return new BaseResponse<>(cover != null ? 1 : 0, cover != null ? "成功" : "失败", cover);
    }

    @RequestMapping("/getAll")
    @Override
    public BaseResponse<List<User>> getAll() {
        List<User> coverList = dataService.getAll();
        return new BaseResponse<>(coverList != null ? 1 : 0, coverList != null ? "成功" : "失败", coverList);
    }

    @RequestMapping("/getAllPage")
    @Override
    public PageBaseResponse<List<User>> getAllPage(int pageNum) {
        //pageNum:表示第几页  pageSize:表示一页展示的数据
        PageHelper.startPage(pageNum, 20);
        List<User> list = dataService.getAll();
        //将查询到的数据封装到PageInfo对象
        PageInfo<User> pageInfo = new PageInfo(list, 3);
        return new PageBaseResponse<>(0, "success", pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPages());
    }


    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public BaseResponse<String> updateInfo(@RequestBody User user) {
        User myUser = dataService.get(user.getId());
        if (user.getBgImg() != null && user.getBgImg().length() > 0) {
            myUser.setBgImg(user.getBgImg());
        }
        if (user.getAvatarPath() != null && user.getAvatarPath().length() > 0) {
            myUser.setAvatarPath(user.getAvatarPath());
        }
        int sqlCode = dataService.update(myUser);
        return new BaseResponse<>(sqlCode, sqlCode > 0 ? "更新成功 " : "失败", "");
    }

}
