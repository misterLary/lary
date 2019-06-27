package com.service;

import com.cjb.Model.UserModel;

import java.util.List;

public interface UserService {

    void saveUser(UserModel userInfo);

    List<UserModel> getUserList(UserModel userInfo);

}
