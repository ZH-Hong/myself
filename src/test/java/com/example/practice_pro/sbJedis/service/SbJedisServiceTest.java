package com.example.practice_pro.sbJedis.service;

import com.example.practice_pro.sbJedis.dto.CodeDTO;
import com.example.practice_pro.sbJedis.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@RequiredArgsConstructor
class SbJedisServiceTest {

    @Autowired
    private SbJedisService sbJedisService;

    @Test
    void redisCode() {
        System.out.println(sbJedisService.redisCode(111111111111l));
    }
    @Test
    void checkCode(){
        final CodeDTO codeDTO = new CodeDTO();
        codeDTO.setCode("056179");
        codeDTO.setPhone(111111111111l);
        System.out.println(sbJedisService.checkCode(codeDTO));
    }

    /**
     * 排它锁检验
    *@author hongguo.zhu
    *@Description 排它锁检验
    *@Date 17:36 2022/1/5
    *@Param
    *@Return
    **/
    @Test
    void checkExclusiveLock(){
        System.out.println("result:" + sbJedisService.checkExclusiveLock("testKey"));
    }

    /**
     * 排它锁检验UUID
     *@author hongguo.zhu
     *@Description 排它锁检验 UUID
     *@Date 17:36 2022/1/5
     *@Param
     *@Return
     **/
    @Test
    void checkExclusiveLockByUUID(){
        System.out.println("result:" + sbJedisService.checkExclusiveLockByUUID("testKey"));
    }
}