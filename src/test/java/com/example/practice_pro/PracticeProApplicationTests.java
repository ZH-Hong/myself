package com.example.practice_pro;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.practice_pro.dao.UserMapper;
import com.example.practice_pro.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.portable.ValueOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
class PracticeProApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserEntity userEntity;

	@Test
	void contextLoads() {
	}

	@Test
	public void testSelectAll(){
		System.out.println("Test Mybatis-Plus");
		QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
		List<UserEntity> userEntities = userMapper.selectList(queryWrapper);
		userEntities.forEach(item -> {
			System.out.println(item.getId());
			System.out.println(item.getName());
			System.out.println(item.getAge());
			System.out.println(item.getEmail());
		});
	}
	@Test
	public void testInsert(){
		userEntity.setName("张三1");
		userEntity.setAge(20);
		userEntity.setEmail("1@git.com");
		userMapper.insert(userEntity);
	}
	@Test
	public void testRemove(){
		QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
		userEntityQueryWrapper.eq("id", 1464142479651684353L);
		userMapper.delete(userEntityQueryWrapper);
	}
	@Test
	public void testUpdate(){
		UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();

		userEntity.setEmail("111111123@163.com");
		userEntity.setVersion(3);
		updateWrapper.eq("id", 1464142479651684353L).and(i -> i.eq("version", 3));
		userMapper.update(userEntity, updateWrapper);
	}

	@Test
	public void testPage(){
		QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
		Page<UserEntity> page = new Page<>();
		page.setCurrent(2);
		page.setSize(3);
		userMapper.selectPage(page, userEntityQueryWrapper);

	}


}
