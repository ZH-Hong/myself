package com.example.practice_pro.sbJedis.service;

import com.example.practice_pro.sbJedis.dto.CodeDTO;

/**
 * 测试 RedisService
 * @InterfaceName SbJedisService
 * @Description 测试 RedisService
 * @Author hongguo.zhu
 * @Date 2021/12/30 9:43
 * @Version 1.0
 */
public interface SbJedisService {

    /**
     * 随机生成n位验证码
    *@author hongguo.zhu
    *@Description 随机生成n位验证码
    *@Date 9:59 2021/12/30
    *@Param codeDTO
    *@Return
     * @return java.lang.String
    **/
    String redisCode(Long phone);

    /**
     * 校验验证码
    *@author hongguo.zhu
    *@Description 校验验证码
    *@Date 14:54 2021/12/30
    *@Param
     * @param codeDTO
    *@Return
     * @return java.lang.String
    **/
    String checkCode(CodeDTO codeDTO);
}
