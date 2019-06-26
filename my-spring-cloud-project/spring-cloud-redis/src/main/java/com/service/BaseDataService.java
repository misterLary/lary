package com.service;

import com.cjb.util.ResponseBody;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-baseData")
public interface BaseDataService {

    @RequestMapping(value = "/user/getUsers")
    ResponseBody getUserList(@RequestParam("userName") String userName);

}
