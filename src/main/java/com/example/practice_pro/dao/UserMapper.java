package com.example.practice_pro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.practice_pro.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description User Mapper 文件
 * @Author hongguo.zhu
 * @Date 2021/11/26 15:17
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
