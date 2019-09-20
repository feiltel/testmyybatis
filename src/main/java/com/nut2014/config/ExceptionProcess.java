package com.nut2014.config;

import com.nut2014.entity.MyLog;
import com.nut2014.pojo.BaseResponse;
import com.nut2014.service.MyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionProcess {
    @Autowired
    MyLogService logService;

    //拦截所有的异常 返回数据 并写入操作日志
    @ExceptionHandler
    public BaseResponse<String> allException(Throwable t) {
        System.out.println(t.getMessage());
        logService.add(new MyLog(0, "异常", t.getMessage()));
        return new BaseResponse<>(0, t.getMessage(), null);
    }
}
