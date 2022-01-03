package com.example.practice_pro.sbJedis.util;

import com.example.practice_pro.advice.exception.MyBusinessException;
import com.example.practice_pro.sbJedis.dto.ProductDTO;
import com.example.practice_pro.sbJedis.dto.SecKillUserDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Collections;
import java.util.List;

/**
 * 秒杀lua脚本工具
 * @ClassName SecKill_ScriptUtil
 * @Description 秒杀lua脚本工具
 * @Author 16967
 * Date 2022/1/2 22:58
 * @Version 1.0
 */
public class SecKill_ScriptUtil {

    public String doSecKillByScript(StringRedisTemplate stringRedisTemplate, SecKillUserDTO secKillUserDTO, ProductDTO productDTO){
        String secKillScript ="local userid=KEYS[1];\r\n" +
                "local prodid=KEYS[2];\r\n" +
                "local productRepositoryKey=" + productDTO.getProductKey() +
                "local usersKey=" + secKillUserDTO.getUserId() +
                "local userExists=stringRedisTemplate.call(\"sismember\",usersKey,userid);\r\n" +
                "if tonumber(userExists)==1 then \r\n" +
                "   return 2;\r\n" +
                "end\r\n" +
                "local num= stringRedisTemplate.call(\"get\" ,productRepositoryKey);\r\n" +
                "if tonumber(num)<=0 then \r\n" +
                "   return 0;\r\n" +
                "else \r\n" +
                "   stringRedisTemplate.call(\"decr\",productRepositoryKey);\r\n" +
                "   stringRedisTemplate.call(\"sadd\",usersKey,userid);\r\n" +
                "end\r\n" +
                "return 1" ;
    /*    String sha1=  jedis.scriptLoad(secKillScript);
        Object result= jedis.evalsha(sha1, 2, uid,prodid);*/
        //创建脚本对象
        DefaultRedisScript<List> script = new DefaultRedisScript<>();
        script.setResultType(List.class);
        //加载lua脚本
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource(secKillScript)));
        // 使用；第一个参数是脚本，第二个参数是redis key列表，第三个可变长度参数是脚本的参数，具体依据脚本而定
        List executeResult = stringRedisTemplate.execute(script, Collections.singletonList(""), secKillUserDTO.getUserId(), secKillUserDTO.getProId());
        String reString=String.valueOf(executeResult);
        if ("0".equals( reString )  ) {
            throw new MyBusinessException("商品已经售完！");
        }else if("1".equals( reString )  )  {
            return "购买成功！";
        }else if("2".equals( reString )  )  {
            throw new MyBusinessException("用户已经购买过！");
        }else{
            throw new MyBusinessException("购买异常！");
        }
    }
}
