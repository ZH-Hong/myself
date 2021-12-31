package com.example.practice_pro.sbJedis.service.impl;

import com.example.practice_pro.advice.exception.MyBusinessException;
import com.example.practice_pro.sbJedis.dto.CodeDTO;
import com.example.practice_pro.sbJedis.service.SbJedisService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 测试 RedisService
 * @ClassName SpringBootJedis
 * @Description 测试 RedisService
 * @Author hongguo.zhu
 * @Date 2021/12/30 9:23
 * @Version 1.0
 */
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
