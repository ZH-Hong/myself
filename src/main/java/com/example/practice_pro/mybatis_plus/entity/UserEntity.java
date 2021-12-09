package com.example.practice_pro.mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ClassName UserEntity
 * @Description User实体类
 * @Author hongguo.zhu
 * @Date 2021/11/26 15:14
 * @Version 1.0
 */

@Data
@Component
@TableName("tb_user")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {
    public UserEntity(String name) {
        this.name = name;
    }

    @TableId(type = IdType.ASSIGN_ID, value = "id")
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @TableField(fill = FieldFill.INSERT, value = "delete_flag")
    @TableLogic(value = "1", delval = "0")
    private Integer deleteFlag;

    @Version
    @TableField(fill = FieldFill.INSERT_UPDATE, value = "version")
    private Integer version;

    public static void main(String[] args) {
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(new UserEntity("张三"));
        userEntities.add(new UserEntity("李四"));
        userEntities.add(new UserEntity("王五"));
        Stream<UserEntity> streams = userEntities.stream();
        System.out.println(streams);
        Stream<String> stringStream = streams.map(UserEntity::getName);
        stringStream.forEach(item -> System.out.println(item));
    }
}
