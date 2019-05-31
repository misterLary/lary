package com.service;

import com.entity.UserInfo;

import java.util.List;

public interface UserService {

    void saveUser(UserInfo userInfo);

    List<UserInfo> getUserList(UserInfo userInfo);

}
