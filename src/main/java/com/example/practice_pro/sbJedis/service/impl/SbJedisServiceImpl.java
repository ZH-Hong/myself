package com.example.practice_pro.sbJedis.service.impl;

import com.example.practice_pro.advice.exception.MyBusinessException;
import com.example.practice_pro.sbJedis.dto.CodeDTO;
import com.example.practice_pro.sbJedis.service.SbJedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 测试 RedisService
 * @ClassName SpringBootJedis
 * @Description 测试 RedisService
 * @Author hongguo.zhu
 * @Date 2021/12/30 9:23
 * @Version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SbJedisServiceImpl implements SbJedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.lock-key}")
    private String lockKey;

    @Value("${properties.code.num}")
    private Integer num;

    @Value("${properties.code.code-key-suffix}")
    private String codeSuffix;

    @Value("${properties.code.phone-key-suffix}")
    private String phoneSuffix;



    @Override
    public String redisCode(Long phone) {
        return returnData(phone, num);
    }

    @Override
    public String checkCode(CodeDTO codeDTO) {
        final ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        String codeKey = codeDTO.getPhone() + codeSuffix;
//        String s = stringStringValueOperations.get("111111111111phone_");
        Long expire = stringStringValueOperations.getOperations().getExpire(codeKey);
        if(expire < 0){
            throw new MyBusinessException("验证码已失效，请重新获取！");
        }
        String s = stringStringValueOperations.get(codeKey);
        if(!StringUtils.equals(codeDTO.getCode(), s)){
            throw new MyBusinessException("验证码错误，请重试！");
        }
        stringStringValueOperations.getOperations().delete(codeKey);
        return "成功！";
    }

    /**
     * 排它锁检验
    *@author hongguo.zhu
    *@Description 排它锁检验
    *@Date 17:14 2022/1/5
    *@Param
     * @param key
    *@Return
     * @return java.lang.String
    **/
    @Override
    public String checkExclusiveLock(String key) {
        if(StringUtils.isEmpty(key)){
            throw new MyBusinessException("key非法！");
        }
        String checkLock = null;
        //尝试重试5次操作数据
        try {
            for (int i = 0; i < 5; i++) {
                checkLock = stringRedisTemplate.opsForValue().get(key);
                if(StringUtils.isEmpty(checkLock)){
                    stringRedisTemplate.opsForValue().setIfAbsent(key, "locked", 5 * 60, TimeUnit.SECONDS);
                    return "锁状态：解锁。并设置锁";
                }
                System.out.println("锁存在，尝试等待并进行重试...重试次数：" + (i + 1));
                Thread.sleep(3000);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        throw new MyBusinessException("锁已存在，且超过最大重试次数，请等待后重试！");
    }

    /**
     * 排它锁检验，通过UUID
     *@author hongguo.zhu
     *@Description 排它锁检验，通过UUID
     *@Date 17:14 2022/1/5
     *@Param
     * @param key
     *@Return
     * @return java.lang.String
     **/
    @Override
    public String checkExclusiveLockByUUID(String key) {
        log.info("进入UUID.....");
        if(StringUtils.isEmpty(key)){
            throw new MyBusinessException("key非法！");
        }
        String checkLock = null;
        String localUUID = null;
        //尝试重试5次操作数据
        try {
            for (int i = 0; i < 5; i++) {
                checkLock = stringRedisTemplate.opsForValue().get(lockKey);
                if(StringUtils.isEmpty(checkLock)){
                    localUUID = UUID.randomUUID().toString();
                    stringRedisTemplate.opsForValue().setIfAbsent(lockKey, localUUID, 5 * 60, TimeUnit.SECONDS);
                    //加锁后进行操作
                    String value = String.valueOf(new Random().nextInt(10));
                    stringRedisTemplate.opsForValue().set(key, "value".concat(value));
                    log.info("key：{}，value：{}，UUID：{}，", key, value, localUUID);
                    if(StringUtils.equals(stringRedisTemplate.opsForValue().get(lockKey), localUUID)){
                        //在释放删除锁之前，引入lua脚本，保证原子性
                        String script = "if redis.call('get', KEYS[1]) == ARGV[1] " +
                                "then return redis.call('del', KEYS[1]) else return 0 end";
                        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
                        redisScript.setScriptText(script);
                        //类型转换设置为Long，lua脚本返回的字符串0，转换为Long类型接收，正常会返回String类型，Long集合接收会报错
                        redisScript.setResultType(Long.class);
                        //参数：第一个lua脚本；第二个是需要判断的key；第三个是key对应的值;
                        stringRedisTemplate.opsForValue().getOperations().execute(redisScript, Arrays.asList(lockKey), localUUID);
                        log.info("{}在有效期，{}操作完毕，并解除锁", lockKey, key);
                        return "锁状态：解锁。并设置锁";
                    }
                }
                log.info("锁存在，尝试等待并进行重试...重试次数：{}", (i + 1));
                Thread.sleep(3000);
            }
        } catch (Exception e){
            throw new MyBusinessException(e.getMessage());
        }
        throw new MyBusinessException("当前有其他服务在操作数据，且超过最大重试次数，请等待后重试！");
    }

    /**
     * 随机生成n位验证码
    *@author hongguo.zhu
    *@Description 随机生成n位验证码
    *@Date 9:56 2021/12/30
    *@Param
     * @param num
    *@Return
     * @return java.lang.String
    **/
    private String createCode(Integer num){
        if (num == 0 || num == null){
            throw new MyBusinessException("传递验证码格式失败！");
        }
        Random random = new Random();
        String codeStr = "";
        for (int i = 0; i < num; i++) {
            codeStr += random.nextInt(10);
        }
        return  codeStr;
    }

    private String returnData(Long phone, Integer num){
        final ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        String codeKey = phone + codeSuffix;
        String phoneKey = phone + phoneSuffix;
        String phoneNumValue = stringStringValueOperations.get(phoneKey);
        final String code = createCode(num);
        if (StringUtils.isEmpty(phoneNumValue)){
            phoneNumValue = "0";
            stringStringValueOperations.set(phoneKey, "0", 1, TimeUnit.DAYS);
            stringStringValueOperations.set(codeKey, code, 5 * 60, TimeUnit.SECONDS);
        }
        if(Integer.parseInt(phoneNumValue) > 2){
            throw new MyBusinessException("当天获取验证码次数过多！");
        }
        stringStringValueOperations.increment(phoneKey);
        stringStringValueOperations.set(codeKey, code, 5 * 60, TimeUnit.SECONDS);
        return code;
    }
}
