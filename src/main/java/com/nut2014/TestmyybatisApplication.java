package com.nut2014;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.nut2014.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class TestmyybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestmyybatisApplication.class, args);
    }

}
