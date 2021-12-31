package com.example.practice_pro.sbJedis.controller;

import com.example.practice_pro.advice.response.ApiResponse;
import com.example.practice_pro.sbJedis.dto.CodeDTO;
import com.example.practice_pro.sbJedis.service.SbJedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码测试Controller
 * @ClassName SBJedisController
 * @Description 验证码测试Controller
 * @Author hongguo.zhu
 * @Date 2021/12/30 9:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/required")
@RequiredArgsConstructor
public class SbJedisController {

    @Autowired
    private final SbJedisService sbJedisService;

    @GetMapping("/createCode")
    public ApiResponse<String> createCode(@RequestBody Long phone){
        return ApiResponse.responseOk(sbJedisService.redisCode(phone));
    }
}
