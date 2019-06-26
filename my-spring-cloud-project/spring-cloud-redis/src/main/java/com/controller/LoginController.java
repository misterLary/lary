package com.controller;

import com.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private BaseDataService baseDataService;

    @RequestMapping("/getUsers")
    public Object getUsers(String userName){
        try{
            return baseDataService.getUserList(userName);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getFeignUsers")
    public Object getFeignUsers(String userName){
        try{
            return baseDataService.getUserList(userName);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
