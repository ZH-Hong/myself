package com.example.practice_pro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.practice_pro.mybatis_plus.service.dao")
public class PracticeProApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeProApplication.class, args);
	}

}
