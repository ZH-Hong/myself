package com.example.practice_pro.sbJedis.service;

import com.example.practice_pro.sbJedis.dto.SecKillUserDTO;

/**
 * 秒杀练习Service
 * @InterfaceName SecKillService
 * @Description 秒杀练习Service
 * @Author hongguo.zhu
 * @Date 2021/12/31 11:32
 * @Version 1.0
 */
public interface SecKillService {

    /**
     * 秒杀案例
    *@author hongguo.zhu
    *@Description 秒杀案例
    *@Date 11:36 2021/12/31
    *@Param
     * @param userId
    *@Return
    **/
    SecKillUserDTO secKill(Long userId);
}
