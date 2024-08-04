package com.azir.mybatisinterceptor.controller;

import com.azir.mybatisinterceptor.entity.UserInfo;
import com.azir.mybatisinterceptor.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangshukun
 * @date 2024/8/4
 */
@RestController
@RequestMapping("/user-info")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/getAllUserInfo")
    public List<UserInfo> getAllUserInfo(){
        return userInfoService.getList();
    }

    @GetMapping("/getUserInfoById")
    public UserInfo getUserInfoById(Integer id){
        return userInfoService.getById(id);
    }

    @PostMapping("/addUserInfo")
    public void addUserInfo(@RequestBody UserInfo userInfo){
        userInfoService.add(userInfo);
    }

    @PutMapping("/updateUserInfo")
    public void updateUserInfo(@RequestBody UserInfo userInfo){
        userInfoService.update(userInfo);
    }
}
