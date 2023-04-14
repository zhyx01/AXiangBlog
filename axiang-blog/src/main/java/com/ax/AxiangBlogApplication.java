package com.ax;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class AxiangBlogApplication {

    public static void main(String[] args) {

        SpringApplication.run(AxiangBlogApplication.class, args);
    }
}