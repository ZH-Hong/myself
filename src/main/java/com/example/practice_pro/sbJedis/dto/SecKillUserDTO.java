package com.example.practice_pro.sbJedis.dto;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * 秒杀案例DTO
 * @ClassName SeckillUserDTO
 * @Description 秒杀案例DTO
 * @Author hongguo.zhu
 * @Date 2021/12/31 11:38
 * @Version 1.0
 */
@Data
@Component
@ToString
public class SecKillUserDTO {

    /**
     * 传递的用户id
     */
    @NotEmpty(message = "用户id信息传递失败！")
    @Length(min = 4, max = 10, message = "非法传递参数！")
    private Long userId;

    /**
     * 传递的订单号
     */
    @NotBlank(message = "订单信息传递失败！")
    private String proId;
}
