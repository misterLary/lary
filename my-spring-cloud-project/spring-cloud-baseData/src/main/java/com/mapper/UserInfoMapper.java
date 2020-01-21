package com.mapper;

import com.cjb.model.UserModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {

    List<UserModel> queryByCondition(UserModel userInfo);

    int queryCountByCondition(UserModel userInfo);

    int saveData(UserModel userInfo);

    int deleteById(@Param("userId") int userId);

    int batchDeleteData(List<Integer> userIds);

    int updateData(UserModel userInfo);

}
