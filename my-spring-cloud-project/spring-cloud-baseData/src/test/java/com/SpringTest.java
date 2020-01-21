package com;

import com.cjb.model.UserModel;
import com.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={BaseDataApplication.class})
public class SpringTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveUser(){
        UserModel userInfo = new UserModel();
        userInfo.setUserId(1);
        userInfo.setUserName("1");
        userInfo.setUserAddress("2");
        userService.saveUser(userInfo);
    }

    @Test
    public void getUserList(){
       List<UserModel> userInfoList = userService.getUserList(null);
       if(CollectionUtils.isNotEmpty(userInfoList)){
           userInfoList.forEach(val -> {
            System.out.println(val.getUserName());
           });
       }
    }


}
