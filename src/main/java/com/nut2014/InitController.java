package com.nut2014;

import com.nut2014.config.ConfigResource;
import com.nut2014.entity.AppVersion;
import com.nut2014.entity.User;
import com.nut2014.pojo.BaseResponse;
import com.nut2014.service.AppVersionService;
import com.nut2014.service.TagService;
import com.nut2014.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {


    @Autowired
    UserService userService;

    @Autowired
    TagService tagService;

    @Autowired
    ConfigResource configResource;

    @Autowired
    AppVersionService appVersionService;

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping("/init")
    public BaseResponse<String> init() {

        //初始化插入用户名
        User user = new User("admin", "123456", "管理员", null);
        if (user.getAvatarPath() == null || user.getAvatarPath().isEmpty()) {
            user.setAvatarPath(configResource.getDefaultAvatarPath());
        }
        if (user.getBgImg() == null || user.getBgImg().isEmpty()) {
            user.setBgImg(configResource.getDefaulUserBg());
        }
        int sqlCode = userService.add(user);
        System.out.println(sqlCode == 1 ? "插入成功" : "插入失败");

        //插入版本信息
        appVersionService.add(
                new AppVersion("第一个版本", 1, "0.0.1",
                        configResource.getServerHost() + configResource.getFileLocation() + "app.apk"));
        return new BaseResponse<>(1, "初始化成功", "");
    }
}
