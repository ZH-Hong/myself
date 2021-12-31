package com.example.practice_pro.sbJedis.service;

import com.example.practice_pro.sbJedis.dto.SecKillUserDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class SecKillServiceTest {

    @Autowired
    private SecKillService secKillService;

    @Test
    void secKill() {

        SecKillUserDTO secKillUserDTO = secKillService.secKill(1114l);
        System.out.println(secKillUserDTO);
    }
}