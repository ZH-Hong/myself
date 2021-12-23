package com.example.practice_pro.sunCode.service.impl;

import com.example.practice_pro.sunCode.service.ImgService;
import com.example.practice_pro.sunCode.util.SunCodeUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ImgServiceImpl
 * @Description TODO
 * @Author hongguo.zhu
 * @Date 2021/12/9 14:24
 * @Version 1.0
 */
@Service
public class ImgServiceImpl implements ImgService {

    private static final String APPID = null;
    private static final String SECRET = null;

    @Override
    public void obtainSunCode(HttpServletRequest request, HttpServletResponse response) {
//        ImgUtils imgUtils = new ImgUtils();
//        imgUtils.getFile(request,response);
        SunCodeUtils sunCodeUtils = new SunCodeUtils();
        sunCodeUtils.createSunCode(APPID, SECRET, request, response);
    }
}
