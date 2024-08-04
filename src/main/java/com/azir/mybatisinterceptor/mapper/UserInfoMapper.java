package com.azir.mybatisinterceptor.mapper;

import com.azir.mybatisinterceptor.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhangshukun
 * @since 2024/08/02
 */
@Mapper
public interface UserInfoMapper {

    List<UserInfo> getAllUserInfo();

    UserInfo getUserInfo(Integer id);

    void addUserInfo(UserInfo userInfo);

    void updateUserInfo(UserInfo userInfo);
}
