package com.example.practice_pro.sbJedis.dto;

import lombok.Data;

/**
 * 验证码DTO
 * @ClassName CodeDTO
 * @Description 验证码DTO
 * @Author hongguo.zhu
 * @Date 2021/12/30 14:45
 * @Version 1.0
 */
@Data
public class CodeDTO {

    /**
     * 手机号
     */
    private Long phone;

    /**
     * 验证码
     */
    private String code;
}
