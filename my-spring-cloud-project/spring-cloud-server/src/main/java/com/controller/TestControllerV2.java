package com.controller;

import com.config.ApiVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiVersion(2)
@RequestMapping("{version}/test")
//@RequestMapping("/test")
public class TestControllerV2 {

    @RequestMapping(value = "/getMessage")
    public String getMessage(){
        return "i got you v2";
    }

}
