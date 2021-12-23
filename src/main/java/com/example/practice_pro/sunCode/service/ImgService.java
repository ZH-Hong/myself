package com.example.practice_pro.sunCode.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ImgService
 * @Description TODO
 * @Author hongguo.zhu
 * @Date 2021/12/9 14:21
 * @Version 1.0
 */
public interface ImgService {

    void obtainSunCode(HttpServletRequest request, HttpServletResponse response);
}
