package com.mappers;

import com.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserInfoMapper {

    List<UserInfo> queryByCondition(UserInfo userInfo);

    int queryCountByCondition(UserInfo userInfo);

    int saveData(UserInfo userInfo);

    int deleteById(@Param("userId") int userId);

    int batchDeleteData(List<Integer> userIds);

    int updateData(UserInfo userInfo);

}
