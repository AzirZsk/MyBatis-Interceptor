package com.azir.mybatisinterceptor.service;

import com.azir.mybatisinterceptor.entity.UserInfo;
import com.azir.mybatisinterceptor.mapper.UserInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangshukun
 * @date 2024/8/4
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoMapper userInfoMapper;

    public List<UserInfo> getList() {
        return userInfoMapper.getAllUserInfo();
    }

    public UserInfo getById(Integer id) {
        return userInfoMapper.getUserInfo(id);
    }

    public void add(UserInfo userInfo) {
        userInfoMapper.addUserInfo(userInfo);
    }

    public void update(UserInfo userInfo) {
        userInfoMapper.updateUserInfo(userInfo);
    }
}
