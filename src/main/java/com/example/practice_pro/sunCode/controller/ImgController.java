package com.example.practice_pro.sunCode.controller;

import com.example.practice_pro.sunCode.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ImgController
 * @Description TODO
 * @Author hongguo.zhu
 * @Date 2021/12/9 14:27
 * @Version 1.0
 */
@RestController
public class ImgController {

    @Autowired
    private ImgService imgService;

    @GetMapping("/getImg")
//    @RequestParam(name = "appid", required = true) String appid,
//    @RequestParam(name = "secret", required = true) String secret,
    public void getImg(HttpServletRequest request, HttpServletResponse response){
        imgService.obtainSunCode(request, response);
    }
}
