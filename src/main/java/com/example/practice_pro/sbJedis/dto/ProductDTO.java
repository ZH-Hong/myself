package com.example.practice_pro.sbJedis.dto;

import lombok.Data;

/**
 * 商品DTO
 * @ClassName ProductDTO
 * @Description 商品DTO
 * @Author 16967
 * Date 2022/1/2 14:05
 * @Version 1.0
 */
@Data
public class ProductDTO {

    /**
     * 商品key
     */
    private String productKey;

    /**
     * 商品库存
     */
    private String productQuantity;
}
