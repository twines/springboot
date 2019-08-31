package com.hanyun.lee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@MapperScan("com.hanyun.lee.mapper") //扫描的mapper
public class LeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeeApplication.class, args);
    }

}
