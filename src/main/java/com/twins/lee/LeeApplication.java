package com.twins.lee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.twins.lee.mapper") //扫描的mapper
public class LeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeeApplication.class, args);
    }

}
