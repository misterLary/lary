package com.service.impl;

import com.cjb.Model.UserModel;
import com.mappers.UserInfoMapper;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void saveUser(UserModel userInfo) {
        userInfoMapper.saveData(userInfo);
    }

    @Override
    public List<UserModel> getUserList(UserModel userInfo) {
        return userInfoMapper.queryByCondition(userInfo);
    }
}
