package com.example.practice_pro.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @ClassName JedisTest
 * @Description TODO
 * @Author hongguo.zhu
 * @Date 2021/12/24 9:17
 * @Version 1.0
 */
public class JedisTest {

    private final Jedis jedis = new Jedis("localhost", 6379);

    @Test
    public void connectTest(){
        jedis.auth("123456");
        String ping = jedis.ping();
        System.out.println(ping);
    }

    @Test
    public void stringTest(){
        jedis.auth("123456");
        jedis.select(1);
//        批量添加
        jedis.mset("k1", "n1", "k2", "n2", "k3", "n3");
        List<String> mget = jedis.mget("k1", "k2");
        System.out.println(mget);
        jedis.set("k1", "ewfrgrwgervwcefefe");
        //获取字符串长度
        System.out.println(jedis.strlen("k1"));
        //指定索引进行替换
        System.out.println(jedis.getrange("k1", 2, 5));
        //指定位置替换
        jedis.setrange("k1", 5, "stringRange");
        System.out.println("指定位置替换k1：" + jedis.get("k1"));
        //操作数字
        jedis.set("num", "1");
        System.out.println("num原值：" + jedis.get("num"));
        System.out.println("num增加：" + jedis.incr("num"));
        System.out.println("num增加指定增量：" + jedis.incrBy("num", 20));
    }

    @Test
    public void listTest(){
        jedis.auth("123456");
        jedis.lpush("llist", "v1", "v2", "v3");
        System.out.println("list:" + jedis.lrange("llist", 0, -1));
        //弹出元素，值空键亡
        System.out.println("list左弹出：" + jedis.lpop("llist"));
        System.out.println("输出list：" + jedis.lrange("llist", 0, -1));


    }

    @Test
    public void setTest(){
        jedis.auth("123456");
        jedis.sadd("setList", "v1", "v2", "v3");
        jedis.sadd("setList1", "v3", "v4", "v5");
        System.out.println("setList:" + jedis.smembers("setList"));
        //弹出元素，值空键亡
        System.out.println("setList左弹出：" + jedis.spop("setList"));
        System.out.println("setList集合元素个数：" + jedis.scard("setList"));
        System.out.println("setList随机选取n个元素：" + jedis.srandmember("setList", 2));
        System.out.println("setList和setList1区交集：" + jedis.sinter("setList", "setList1"));
        System.out.println("setList和setList1区并集：" + jedis.sunion("setList", "setList1"));
        System.out.println("setList和setList1区差集：" + jedis.sdiff("setList", "setList1"));


    }

}
