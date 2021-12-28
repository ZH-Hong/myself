package com.example.practice_pro.jedis;

import lombok.val;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @ClassName JedisDemo
 * @Description TODO
 * @Author hongguo.zhu
 * @Date 2021/12/23 11:28
 * @Version 1.0
 */

public class JedisDemo {


    static Jedis jedis = new Jedis("localhost", 6379);

    public static void main(String[] args) {

    }
}
