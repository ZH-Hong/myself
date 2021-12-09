package com.example.practice_pro.mybatis_plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName DateEntity
 * @Description TODO
 * @Author hongguo.zhu
 * @Date 2021/12/2 10:20
 * @Version 1.0
 */
@Data
@Component
@TableName("tb_batchdate")
public class DateEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;
    @JsonFormat(
            timezone = "GMT+8",
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date tDate;


    public DateEntity(){}
    public DateEntity(String name, Date tDate) {
        this.name = name;
        this.tDate = tDate;
    }
}
