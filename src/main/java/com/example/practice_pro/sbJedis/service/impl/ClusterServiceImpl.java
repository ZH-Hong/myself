package com.example.practice_pro.sbJedis.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 集群ServiceImpl
 * @ClassName ClusterServiceImpl
 * @Description 集群ServiceImpl
 * @Author hongguo.zhu
 * @Date 2022/1/5 15:54
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class ClusterServiceImpl {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String clusterCreate(){





        return null;
    }
}
