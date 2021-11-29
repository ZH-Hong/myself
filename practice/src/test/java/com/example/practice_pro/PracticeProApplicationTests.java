package com.example.practice_pro;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.practice_pro.dao.UserMapper;
import com.example.practice_pro.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
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
	//分页查询
	@Test
	public void testPage(){
		QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
		Page<UserEntity> page = new Page<>();
		//当前页
		page.setCurrent(2);
		//页面大小
		page.setSize(2);
		userMapper.selectPage(page, userEntityQueryWrapper);

	}

	//测试乐观锁成功a
	@Test
	public void testOptimisticLocker(){
		//查询用户信息
		UserEntity user	= userMapper.selectById(1);
		//修改用户信息
		user.setName("wushuang");
		user.setAge(23);
		userMapper.updateById(user);
	}

	//批量查询
	@Test
	public void testSelectByBatchId(){
		List<UserEntity> user = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
		user.forEach(System.out::println);

	}

	//条件查询
	@Test
	public void testSelectByBatchIds(){
		HashMap<String,Object> map =new HashMap<>();
		//自定义查询
		map.put("name","wushuang");
		map.put("age",22);
		List<UserEntity> userEntities = userMapper.selectByMap(map);
		userEntities.forEach(System.out::println);

	}
	@Test
	public void testUpdate(){
		UserEntity user = userMapper.selectById(1465234626652549122L);
//		userEntity.setId(1465234626652549122L);
		user.setName("zhangsan11");
		userMapper.updateById(user);
	}


}
