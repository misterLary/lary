package com;

import com.entity.UserInfo;
import com.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={SpringServiceApplication.class})
public class SpringTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveUser(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1);
        userInfo.setUserName("1");
        userInfo.setUserAddress("2");
        userService.saveUser(userInfo);
    }

    @Test
    public void getUserList(){
       List<UserInfo> userInfoList = userService.getUserList(null);
       if(CollectionUtils.isNotEmpty(userInfoList)){
           userInfoList.forEach(val -> {
            System.out.println(val.getUserName());
           });
       }
    }


}
