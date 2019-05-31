package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient//服务端
//@EnableFeignClients
@MapperScan("com.mappers")
public class SpringServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringServiceApplication.class, args);
    }
}
