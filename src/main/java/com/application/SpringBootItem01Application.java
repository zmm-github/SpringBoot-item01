package com.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.application.mapper")
public class SpringBootItem01Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootItem01Application.class, args);
    }

}
