package com.nut2014;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.nut2014.mapper")
@SpringBootApplication
public class TestmyybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestmyybatisApplication.class, args);
    }

}
