package com.nut2014.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nut2014.entity.MyLog;
import com.nut2014.service.MyLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;


//AOP切面 ：多个切面时，@Order(i)注解来标识切面的优先级。i的值越小，优先级越高
@Order(5)
@Aspect
@Component
public class SysAspect {
    @Autowired
    MyLogService logService;

    @Pointcut(value = "execution(public * com.nut2014.controller.*.*(..))")
    public void log(){}


    //统计请求的处理时间
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        startTime.set(System.currentTimeMillis());
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求的内容
        System.out.println("Aspect_URL:"+request.getRequestURL().toString());
        System.out.println("Aspect_Method:"+request.getMethod());
        //logService.add(new MyLog(0,"接口访问",request.getRequestURL().toString()+" 请求方法"+request.getMethod()));
    }

    @AfterReturning(returning = "ret" , pointcut = "log()")
    public void doAfterReturning(Object ret){

        //处理完请求后，返回内容
        String msg= "方法执行时间:"+ (System.currentTimeMillis() - startTime.get())+"  返回值:"+ new Gson().toJson(ret) ;
        System.out.println(msg);
       // logService.add(new MyLog(0,"接口访问",msg));
    }
}
