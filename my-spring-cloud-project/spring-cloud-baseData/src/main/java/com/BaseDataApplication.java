package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.support.FeignUtils;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient//服务端
@EnableFeignClients
@MapperScan("com.mapper")
@Import(FeignUtils.class)
public class BaseDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseDataApplication.class, args);
    }
}
