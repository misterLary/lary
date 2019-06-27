package com.service.impl;

import com.cjb.Model.UserModel;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("userService1")
public class UserServiceImpl implements IUserService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object getUserList() {
        return restTemplate.getForEntity("", UserModel.class);
    }
}
