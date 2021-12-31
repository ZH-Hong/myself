package com.example.practice_pro.sbJedis.service.impl;

import com.example.practice_pro.advice.exception.MyBusinessException;
import com.example.practice_pro.sbJedis.dto.SecKillUserDTO;
import com.example.practice_pro.sbJedis.service.SecKillService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Random;

/**
 * 秒杀练习Impl
 * @ClassName SecKillServiceImpl
 * @Description 秒杀练习Impl
 * @Author hongguo.zhu
 * @Date 2021/12/31 11:34
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class SecKillServiceImpl implements SecKillService {


    @Value("${properties.secKill.user-record-key}")
    private String userRecordKey;

    @Value("${properties.secKill.product-repository-key}")
    private String productRepositoryKey;

    @Value("${properties.secKill.order-number-key}")
    private String orderNumberKey;

    @Value("${properties.secKill.user-key-prefix}")
    private String userKeyPrefix;

    @Value("${properties.secKill.pro-key-prefix}")
    private String proKeyPrefix;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SecKillUserDTO secKillUserDTO;

    @Override
    public SecKillUserDTO secKill(@Validated Long userId) {
        if(userId == null || userId.toString().length() < 4){
            throw new MyBusinessException("用户数据非法！");
        }
        //判断秒杀活动是否开始
        if(stringRedisTemplate.opsForValue().get(productRepositoryKey) == null){
            throw new MyBusinessException("活动暂未开始，请关注活动开始时间！");
        }
        //判断是否重复操作
        if(stringRedisTemplate.opsForSet().isMember(userRecordKey, userId.toString())){
            throw new MyBusinessException("用户已经购买过该商品且购买次数已达上限！");
        }
        //判断剩余量
        if(Integer.parseInt(stringRedisTemplate.opsForValue().get(productRepositoryKey)) < 1){
            throw new MyBusinessException("仓库商品剩余量不足,请等待商家补货！");
        }
        //进行商品购买操作
        stringRedisTemplate.opsForValue().decrement(productRepositoryKey);
        //生成订单号
        String orderNumber = createOrderNumber(userId);
        //将用户信息和订单号存入set
        stringRedisTemplate.opsForSet().add(userRecordKey, userId.toString());
        stringRedisTemplate.opsForSet().add(orderNumberKey, userId + "," +orderNumber);
        secKillUserDTO.setUserId(userId);
        secKillUserDTO.setProId(orderNumber);
        return secKillUserDTO;
    }

    private String createOrderNumber(Long userId){
        String orderNumber = "A" + userId;
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            orderNumber += random.nextInt(10);
        }
        return orderNumber;
    }
}
