package com.example.practice_pro.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

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
public class UserEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @TableField(fill = FieldFill.INSERT, value = "delete_flag")
    @TableLogic(value = "1", delval = "0")
    private Integer deleteFlag;

    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
