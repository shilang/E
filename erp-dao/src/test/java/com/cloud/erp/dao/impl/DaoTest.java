/**
 * @Title:  DaoTest.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月5日  下午3:22:02
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloud.erp.entities.User;

import junit.framework.TestCase;

/**
 * @ClassName  DaoTest
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月5日  下午3:22:02
 *
 */
public class DaoTest extends TestCase{

	private ShiroDaoImpl shiroDao = new ShiroDaoImpl();
	
	private SessionFactory sessionFactory;
	private ApplicationContext ctx;
	
	{	
		ctx = new ClassPathXmlApplicationContext("applicationContext-datasource.xml", "applicationContext.xml");
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
	}
	
	public void test() {
		
		shiroDao.setSessionFactory(sessionFactory);
		
		User user = shiroDao.getUser("admin");
		assertNotNull(shiroDao.getUser("admin"));
		
		System.out.println(user.getName());
		
	}
	
}
