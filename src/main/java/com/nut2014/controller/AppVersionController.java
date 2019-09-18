package com.nut2014.controller;

import com.nut2014.entity.AppVersion;
import com.nut2014.pojo.BaseResponse;
import com.nut2014.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppVersionController {
    private AppVersionService versionService;

    @Autowired
    public AppVersionController(AppVersionService versionService) {
        this.versionService = versionService;
    }

    @RequestMapping("/getVersion")
    public BaseResponse<AppVersion> getVersion() {
        return new BaseResponse<>(1, "success", versionService.getAll().get(0));
    }
}
