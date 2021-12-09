package com.example.practice_pro.mybatis_plus.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.practice_pro.mybatis_plus.entity.DateEntity;
import com.example.practice_pro.mybatis_plus.dao.DateMapper;
import com.example.practice_pro.mybatis_plus.service.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName DateServiceImpl
 * @Description TODO
 * @Author hongguo.zhu
 * @Date 2021/12/2 10:27
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class DateServiceImpl extends ServiceImpl<DateMapper, DateEntity> implements DateService {


    @Override
    public void batchSaveMeth() {
        List<DateEntity> dateEntities = new ArrayList<>();
        LocalDateTime thisDate = LocalDateTime.now();
        for (int i = 0; i < 12; i++) {
            DateEntity dateEntity = new DateEntity();
            dateEntity.setName("张三");
            dateEntity.setTDate(Date.from(thisDate.plusMonths(i).atZone(ZoneId.systemDefault()).toInstant()));
        dateEntities.add(dateEntity);
        }
        this.saveBatch(dateEntities);
    }
}
