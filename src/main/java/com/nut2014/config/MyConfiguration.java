package com.nut2014.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    ConfigResource configResource;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        //其中OTA表示访问的前缀。"file:***"是文件真实的存储路径 创建本地文件与url的映射
        registry.addResourceHandler(configResource.getFileLocation()+"**").addResourceLocations("file:"+configResource.getFileUploadPath());
    }
}
