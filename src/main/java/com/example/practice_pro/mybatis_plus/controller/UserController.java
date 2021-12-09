package com.example.practice_pro.mybatis_plus.controller;

import com.example.practice_pro.mybatis_plus.dao.UserMapper;
import com.example.practice_pro.mybatis_plus.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author hongguo.zhu
 * @Date 2021/12/1 10:10
 * @Version 1.0
 */

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
    public List<UserEntity> test(){
        return userMapper.selectList(null);
    }
}
