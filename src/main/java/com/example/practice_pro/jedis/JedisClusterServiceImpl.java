package com.example.practice_pro.jedis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;

/**
 * 集群ServiceImpl
 * @ClassName JedisClusterServiceImpl
 * @Description 集群ServiceImpl
 * @Author hongguo.zhu
 * @Date 2022/1/5 15:54
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class JedisClusterServiceImpl {

    public String clusterCreate(){
        //也可以不用set集合，add的目的是为了加入多组ip和端口，但集群特点是无中心化，所以加入一个也可；
        HashSet<HostAndPort> hostAndPorts = new HashSet<>();
        hostAndPorts.add(new HostAndPort("127.0.0.1", 6379));
        JedisCluster jedisCluster = new JedisCluster(hostAndPorts);
        jedisCluster.set("k1", "v1");
        return jedisCluster.get("k1");
    }
}
