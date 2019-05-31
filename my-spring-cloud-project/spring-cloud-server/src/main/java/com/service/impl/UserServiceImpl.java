package com.service.impl;

import com.entity.UserInfo;
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
    public void saveUser(UserInfo userInfo) {
        userInfoMapper.saveData(userInfo);
    }

    @Override
    public List<UserInfo> getUserList(UserInfo userInfo) {
        return userInfoMapper.queryByCondition(userInfo);
    }
}
