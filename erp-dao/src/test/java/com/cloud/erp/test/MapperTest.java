package com.cloud.erp.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cloud.erp.entities.table.UserInfo;
import com.cloud.erp.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void userMapperTest(){
		UserInfo userInfo = userMapper.getUserById(2);
		
		
		
		System.out.println(userInfo);
	}
}
