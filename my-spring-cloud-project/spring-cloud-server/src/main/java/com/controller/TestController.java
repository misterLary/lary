package com.controller;

import com.config.ApiVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiVersion(1)
@RequestMapping("{version}/test")
//@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/getMessage")
    public String getMessage(){
        return "i got you v1";
    }

}
