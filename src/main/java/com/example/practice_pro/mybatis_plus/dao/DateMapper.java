package com.example.practice_pro.mybatis_plus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.practice_pro.mybatis_plus.entity.DateEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DateMapper extends BaseMapper<DateEntity> {
}
