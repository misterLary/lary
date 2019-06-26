package com.controller;

import com.cjb.util.ResponseBody;
import com.config.ApiVersion;
import com.entity.UserInfo;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUsers")
    public Object getMessage(String userName){
        ResponseBody responseBody = new ResponseBody();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        responseBody.setData(userService.getUserList(userInfo));
        return responseBody;
    }

}
