package com.service;

import com.cjb.model.UserModel;

import java.util.List;

public interface UserService {

    void saveUser(UserModel userInfo);

    List<UserModel> getUserList(UserModel userInfo);

}
