package com.nut2014.controller;

import com.nut2014.entity.User;
import com.nut2014.pojo.BaseResponse;
import com.nut2014.service.TokenService;
import com.nut2014.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    private UserService dataService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping("/login")
    public BaseResponse getUser(String userName, String passWord) {
        User user = dataService.getUser(userName);
        if (user != null) {
            if (user.getPassWord().equalsIgnoreCase(passWord)) {
                user.setToken(tokenService.getToken(user));
                return new BaseResponse<>(1, "登录成功", user);
            }
        }
        return new BaseResponse<>(0, "登录失败,用户名或密码错误", new User());

    }

}
