package com.example.practice_pro.mybatis_plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.practice_pro.mybatis_plus.entity.DateEntity;

/**
 * @ClassName DateService
 * @Description TODO
 * @Author hongguo.zhu
 * @Date 2021/12/2 10:27
 * @Version 1.0
 */
public interface DateService extends IService<DateEntity>  {

    void batchSaveMeth();
}
