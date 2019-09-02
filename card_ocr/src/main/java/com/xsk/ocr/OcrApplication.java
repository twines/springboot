package com.xsk.ocr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class OcrApplication {
         public static void main(String[] args) {
            SpringApplication.run(OcrApplication.class, args);
     }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("5MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("300MB");
        return factory.createMultipartConfig();
    }
}
