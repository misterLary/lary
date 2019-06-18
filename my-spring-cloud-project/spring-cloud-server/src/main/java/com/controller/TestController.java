package com.controller;

import com.cjb.util.ResponseBody;
import com.config.ApiVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiVersion(1)
@RequestMapping("{version}/test")
//@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/getMessage")
    public Object getMessage(){
        ResponseBody responseBody = new ResponseBody();
        responseBody.setData("i got you v1");
        responseBody.setCode("200");
        return responseBody;
    }

}
