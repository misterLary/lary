package com.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean(name = "eurekaRestTemplate")
    @LoadBalanced
    public RestTemplate initEurekaRestTemplate() {
        return new RestTemplate();
    }

    @Bean(name = "httpRestTemplate")
    public RestTemplate initHttpRestTemplate() {
        return new RestTemplate();
    }
}