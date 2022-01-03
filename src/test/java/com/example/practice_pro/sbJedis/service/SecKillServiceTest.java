package com.example.practice_pro.sbJedis.service;

import com.example.practice_pro.sbJedis.dto.ProductDTO;
import com.example.practice_pro.sbJedis.dto.SecKillUserDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class SecKillServiceTest {

    @Autowired
    private SecKillService secKillService;

    @Value("${properties.secKill.product-repository-key}")
    private String productRepositoryKey;

    @Test
    void secKill() {

        SecKillUserDTO secKillUserDTO = secKillService.secKill(1115l);
        System.out.println(secKillUserDTO);
    }

    @Test
    void createProduct(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductKey(productRepositoryKey);
        productDTO.setProductQuantity("99");
        secKillService.createProduct(productDTO);
    }
}